package com.artempact.backend.mysqlArtempact.controller.service;

import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.CreativeSearchLocations;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.PhotoBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.junctionTables.CreativeSearchLocationRepository;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.junctionTables.PhotoBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlGeographic.controller.service.GeographicService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileBusinessControllerService {

    private final TypeOfBusinessRepository typeOfBusinessRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final PhotoBusinessRepository photoBusinessRepository;
    private final CreativeSearchLocationRepository creativeSearchLocationRepository;
    private final GeographicService geographicService;

    public ProfileBusinessControllerService(TypeOfBusinessRepository typeOfBusinessRepository, WorkPreferenceRepository workPreferenceRepository, PhotoBusinessRepository photoBusinessRepository, CreativeSearchLocationRepository creativeSearchLocationRepository, GeographicService geographicService) {
        this.typeOfBusinessRepository = typeOfBusinessRepository;
        this.workPreferenceRepository = workPreferenceRepository;
        this.photoBusinessRepository = photoBusinessRepository;
        this.creativeSearchLocationRepository = creativeSearchLocationRepository;
        this.geographicService = geographicService;
    }

    public void handleTypeOfBusinessUpdate(ProfileBusiness profile, short typeOfBusinessId) {
        typeOfBusinessRepository.findById(typeOfBusinessId).ifPresentOrElse(
                typeOfBusiness -> profile.setTypeOfBusiness(typeOfBusiness),
                () -> new ValidationException("TypeOfBusiness with given ID not found")
        );
    }

    public void handleWorkPreferenceUpdate(ProfileBusiness profile, short workPreferenceId) {
        workPreferenceRepository.findById(workPreferenceId).ifPresentOrElse(
                workPreference -> profile.setWorkPreference(workPreference),
                () -> new ValidationException("WorkPreference with given ID not found")
        );
    }

    public void handleJobSearchLocationsUpdate(ProfileBusiness profile, List<Map<String, Object>> requestedLocations) {
        Set<CreativeSearchLocations> currentLocations = profile.getCreativeSearchLocations();

        // Converti la lista di mappe in un set di JobSearchLocation per facilitare il confronto
        Set<CreativeSearchLocations> requestedLocationSet = requestedLocations.stream().map(map -> {
            CreativeSearchLocations loc = new CreativeSearchLocations();
            loc.setCity((String) map.get("city"));
            loc.setProvince((String) map.get("province"));
            // Aggiungi lat e lon qui se sono presenti nel map
            return loc;
        }).collect(Collectors.toSet());

        // Rimuovi le location che non sono più richieste
        currentLocations.removeIf(existingLocation -> !requestedLocationSet.contains(existingLocation));

        // Aggiungi le nuove location se non esistono già
        requestedLocationSet.forEach(requestedLocation -> {
            boolean exists = currentLocations.stream()
                    .anyMatch(existingLocation -> existingLocation.equals(requestedLocation));

            if (!exists) {
                Optional<List<BigDecimal>> optionalGeo = geographicService.getLatAndLonFromCityAndProvince(requestedLocation.getCity(), requestedLocation.getProvince());
                if (optionalGeo.isEmpty()) {
                    throw new ValidationException("Geographic information for city and province not found.");
                }

                List<BigDecimal> geo = optionalGeo.get();
                double lat = geo.get(0).doubleValue();
                double lon = geo.get(1).doubleValue();

                CreativeSearchLocations newLocation = new CreativeSearchLocations();
                newLocation.setCity(requestedLocation.getCity());
                newLocation.setProvince(requestedLocation.getProvince());
                newLocation.setLat(lat);
                newLocation.setLon(lon);
                newLocation.setProfileBusiness(profile);
                creativeSearchLocationRepository.save(newLocation);
                currentLocations.add(newLocation);
            }
        });
    }

    public void handlePhotoUpdate(ProfileBusiness profile, List<String> photoLinks) {

        Set<PhotoBusiness> currentPhotoLinks = profile.getPhotoBusinesses();

        // Trova i photo link che non sono presenti nella nuova lista e li rimuove
        currentPhotoLinks.removeIf(photoLink -> !photoLinks.contains(photoLink.getPhotoLink()));

        // Trova o crea le nuove foto da aggiungere
        photoLinks.forEach(photoLink -> {
            currentPhotoLinks.stream()
                    .filter(ur -> ur.getPhotoLink().equals(photoLink))
                    .findFirst()
                    .orElseGet(() -> {
                        PhotoBusiness newPhotoLink = new PhotoBusiness((String) photoLink);
                        newPhotoLink.setProfileBusiness(profile);
                        photoBusinessRepository.save(newPhotoLink); // Salva immediatamente la nuova photoLink
                        return newPhotoLink;
                    });
        });
    }

    public ProfileCreative.Locality updateBusinessLocality(String city, String province) {

        Optional<List<BigDecimal>> optionalGeo = geographicService.getLatAndLonFromCityAndProvince(city, province);
        if (!optionalGeo.isPresent()) {
            throw new ValidationException("Geographic information for city and province not found.");
        }

        List<BigDecimal> geo = optionalGeo.get();
        BigDecimal lat = geo.get(0);
        BigDecimal lon = geo.get(1);

        return new ProfileCreative.Locality(city, province, lat.doubleValue(), lon.doubleValue());
    }
}


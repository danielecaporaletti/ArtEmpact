package com.artempact.backend.mysqlArtempact.controller.service;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.junctionTables.JobSearchLocation;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.junctionTables.PhotoCreative;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.junctionTables.*;
import com.artempact.backend.mysqlGeographic.controller.service.GeographicService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileCreativeControllerService {
    
    private final EducationTypeRepository educationTypeRepository;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final PhotoCreativeRepository photoCreativeRepository;
    private final JobSearchLocationRepository jobSearchLocationRepository;
    private final GeographicService geographicService;

    public ProfileCreativeControllerService(EducationTypeRepository educationTypeRepository, TypeOfCreativeRepository typeOfCreativeRepository, WorkPreferenceRepository workPreferenceRepository, PhotoCreativeRepository photoCreativeRepository, JobSearchLocationRepository jobSearchLocationRepository, GeographicService geographicService) {
        this.educationTypeRepository = educationTypeRepository;
        this.typeOfCreativeRepository = typeOfCreativeRepository;
        this.workPreferenceRepository = workPreferenceRepository;
        this.photoCreativeRepository = photoCreativeRepository;
        this.jobSearchLocationRepository = jobSearchLocationRepository;
        this.geographicService = geographicService;
    }

    public void handleEducationTypeUpdate(ProfileCreative profile, short educationTypeId) {
            educationTypeRepository.findById(educationTypeId).ifPresentOrElse(
                    educationType -> profile.setEducationType(educationType),
                    () -> new ValidationException("EducationType with given ID not found")
            );
    }

    public void handleTypeOfCreativeUpdate(ProfileCreative profile, short typeOfCreativeId) {
            typeOfCreativeRepository.findById(typeOfCreativeId).ifPresentOrElse(
                    typeOfCreative -> profile.setTypeOfCreative(typeOfCreative),
                    () -> new ValidationException("TypeOfCreative with given ID not found")
            );
    }

    public void handleWorkPreferenceUpdate(ProfileCreative profile, short workPreferenceId) {
        workPreferenceRepository.findById(workPreferenceId).ifPresentOrElse(
                workPreference -> profile.setWorkPreference(workPreference),
                () -> new ValidationException("WorkPreference with given ID not found")
        );
    }

    public void handleJobSearchLocationsUpdate(ProfileCreative profile, List<Map<String, Object>> requestedLocations) {
        Set<JobSearchLocation> currentLocations = profile.getJobSearchLocations();

        // Converti la lista di mappe in un set di JobSearchLocation per facilitare il confronto
        Set<JobSearchLocation> requestedLocationSet = requestedLocations.stream().map(map -> {
            JobSearchLocation loc = new JobSearchLocation();
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

                JobSearchLocation newLocation = new JobSearchLocation();
                newLocation.setCity(requestedLocation.getCity());
                newLocation.setProvince(requestedLocation.getProvince());
                newLocation.setLat(lat);
                newLocation.setLon(lon);
                newLocation.setProfileCreative(profile);
                jobSearchLocationRepository.save(newLocation);
                currentLocations.add(newLocation);
            }
        });
    }

    public void handlePhotoUpdate(ProfileCreative profile, List<String> photoLinks) {

        Set<PhotoCreative> currentPhotoLinks = profile.getPhotoCreatives();

        // Trova i photo link che non sono presenti nella nuova lista e li rimuove
        currentPhotoLinks.removeIf(photoLink -> !photoLinks.contains(photoLink.getPhotoLink()));

        // Trova o crea le nuove foto da aggiungere
        photoLinks.forEach(photoLink -> {
            currentPhotoLinks.stream()
                    .filter(ur -> ur.getPhotoLink().equals(photoLink))
                    .findFirst()
                    .orElseGet(() -> {
                        PhotoCreative newPhotoLink = new PhotoCreative((String) photoLink);
                        newPhotoLink.setProfileCreative(profile);
                        photoCreativeRepository.save(newPhotoLink); // Salva immediatamente la nuova photoLink
                        return newPhotoLink;
                    });
        });
    }

    public ProfileCreative.Locality updateCreativeLocality(String city, String province) {

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

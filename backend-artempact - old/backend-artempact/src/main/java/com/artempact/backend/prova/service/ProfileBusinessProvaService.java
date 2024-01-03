package com.artempact.backend.prova.service;

import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.ProfileBusinessDTO;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.PhotoBusiness;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.ProfileBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.junctionTables.CreativeSearchLocationRepository;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.junctionTables.PhotoBusinessRepository;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.CreativeSearchLocations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileBusinessProvaService {

    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;
    @Autowired
    private TypeOfBusinessRepository typeOfBusinessRepository;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;
    @Autowired
    private PhotoBusinessRepository photoBusinessRepository;
    @Autowired
    private CreativeSearchLocationRepository creativeSearchLocationRepository;

    @Transactional
    public ProfileBusiness updateProfileBusinessWithPatch(String id, ProfileBusinessDTO profileBusinessDTO) {
        ProfileBusiness existingProfileBusiness = profileBusinessRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        if (profileBusinessDTO.getEmail() != null) {
            existingProfileBusiness.setEmail(profileBusinessDTO.getEmail());
        }
        if (profileBusinessDTO.getPhone() != null) {
            existingProfileBusiness.setPhone(profileBusinessDTO.getPhone());
        }
        if (profileBusinessDTO.getName() != null) {
            existingProfileBusiness.setName(profileBusinessDTO.getName());
        }
        if (profileBusinessDTO.getSurname() != null) {
            existingProfileBusiness.setSurname(profileBusinessDTO.getSurname());
        }
        if (profileBusinessDTO.getDob() != null) {
            existingProfileBusiness.setDob(profileBusinessDTO.getDob());
        }
        if (profileBusinessDTO.getLocality() != null) {
            if (profileBusinessDTO.getLocality().getCity() != null) {
                existingProfileBusiness.getLocality().setCity(profileBusinessDTO.getLocality().getCity());
            }
            if (profileBusinessDTO.getLocality().getProvince() != null) {
                existingProfileBusiness.getLocality().setProvince(profileBusinessDTO.getLocality().getProvince());
            }
        }
        if (profileBusinessDTO.getDescription() != null) {
            existingProfileBusiness.setDescription(profileBusinessDTO.getDescription());
        }
        if (profileBusinessDTO.getMaxDistanceKm() != null) {
            existingProfileBusiness.setMaxDistanceKm(profileBusinessDTO.getMaxDistanceKm());
        }
        if (profileBusinessDTO.getProfileCompletionPercentage() != null) {
            existingProfileBusiness.setProfileCompletionPercentage(profileBusinessDTO.getProfileCompletionPercentage());
        }
        if (profileBusinessDTO.getBusinessName() != null) {
            existingProfileBusiness.setBusinessName(profileBusinessDTO.getBusinessName());
        }
        if (profileBusinessDTO.getTypeOfBusinessId() != null) {
            TypeOfBusiness typeOfBusiness = typeOfBusinessRepository.findById(profileBusinessDTO.getTypeOfBusinessId())
                    .orElseThrow(() -> new EntityNotFoundException("TypeOfBusiness not found"));
            existingProfileBusiness.setTypeOfBusiness(typeOfBusiness);
        }
        if (profileBusinessDTO.getWorkPreferenceId() != null) {
            WorkPreference workPreference = workPreferenceRepository.findById(profileBusinessDTO.getWorkPreferenceId())
                    .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"));
            existingProfileBusiness.setWorkPreference(workPreference);
        }
        if (profileBusinessDTO.getPhotoBusinessesLinks() != null) {
            Set<String> newPhotoLinks = new HashSet<>(profileBusinessDTO.getPhotoBusinessesLinks());
            Set<PhotoBusiness> currentPhotos = existingProfileBusiness.getPhotoBusinesses();
            // Rimuovi le foto non più rilevanti
            currentPhotos.removeIf(photo -> !newPhotoLinks.contains(photo.getPhotoLink()));
            // Aggiungi nuove foto
            newPhotoLinks.forEach(link -> {
                boolean exists = currentPhotos.stream()
                        .anyMatch(photo -> photo.getPhotoLink().equals(link));
                if (!exists) {
                    PhotoBusiness newPhoto = new PhotoBusiness();
                    newPhoto.setPhotoLink(link);
                    newPhoto.setProfileBusiness(existingProfileBusiness);
                    currentPhotos.add(newPhoto);
                }
            });
        }
        if (profileBusinessDTO.getCreativeSearchLocations() != null) {
            // Converti i DTO in un formato più facilmente confrontabile (es. coppie città-provincia)
            Set<Pair<String, String>> newLocations = profileBusinessDTO.getCreativeSearchLocations().stream()
                    .map(dto -> Pair.of(dto.getCity(), dto.getProvince()))
                    .collect(Collectors.toSet());

            // Ottieni le località esistenti e convertile in un formato simile
            Set<Pair<String, String>> existingLocations = existingProfileBusiness.getCreativeSearchLocations().stream()
                    .map(loc -> Pair.of(loc.getCity(), loc.getProvince()))
                    .collect(Collectors.toSet());

            // Rimuovi le località non più rilevanti
            existingProfileBusiness.getCreativeSearchLocations().removeIf(loc ->
                    !newLocations.contains(Pair.of(loc.getCity(), loc.getProvince())));

            // Aggiungi nuove località
            newLocations.forEach(locationPair -> {
                if (!existingLocations.contains(locationPair)) {
                    CreativeSearchLocations newLocation = new CreativeSearchLocations();
                    newLocation.setCity(locationPair.getFirst());
                    newLocation.setProvince(locationPair.getSecond());

                    // Imposta latitudine e longitudine a 100
                    newLocation.setLat(100.0);
                    newLocation.setLon(100.0);

                    newLocation.setProfileBusiness(existingProfileBusiness);
                    existingProfileBusiness.getCreativeSearchLocations().add(newLocation);
                }
            });
        }

        return profileBusinessRepository.save(existingProfileBusiness);
    }
}

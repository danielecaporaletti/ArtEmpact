package com.artempact.backend.artempact.controller.card.creativeSeeksBusiness;

import com.artempact.backend.artempact.controller.HandleAttributesUtility;
import com.artempact.backend.artempact.dto.card.creativeSeeksBusiness.CreativeSeeksBusinessDTO;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocation;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.geoService.AsyncGeoService;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksBusiness.CreativeSeeksBusinessRepository;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocationRepository;
import com.artempact.backend.artempact.repository.repository.lookup.*;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreativeSeeksBusinessService {

    private final HandleAttributesUtility handleAttributesUtility;
    private final AsyncGeoService asyncGeoService;
    private final ProfileCreativeRepository profileCreativeRepository;
    private final EducationTypeRepository educationTypeRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final ProfessionalRelationshipRepository professionalRelationshipRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final TypeOfBusinessRepository typeOfBusinessRepository;
    private final CreativeSeeksBusinessRepository creativeSeeksBusinessRepository;
    private final CreativeSeeksBusinessLocationRepository creativeSeeksBusinessLocationRepository;

    @Transactional
    public Set<CreativeSeeksBusiness> getService(String userId) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        return profileCreative.getCreativeSeeksBusinesses();
    }

    @Transactional
    public CreativeSeeksBusiness postService(String userId, CreativeSeeksBusinessDTO creativeSeeksBusinessDTO) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        CreativeSeeksBusiness creativeSeeksBusiness = new CreativeSeeksBusiness();

        return saveOrUpdateCreativeSeeksBusiness(creativeSeeksBusinessDTO, creativeSeeksBusiness, profileCreative);
    }

    @Transactional
    public CreativeSeeksBusiness patchService(String userId, Long cardId, CreativeSeeksBusinessDTO creativeSeeksBusinessDTO) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        CreativeSeeksBusiness creativeSeeksBusiness = creativeSeeksBusinessRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not belong to the current user"));

        return saveOrUpdateCreativeSeeksBusiness(creativeSeeksBusinessDTO, creativeSeeksBusiness, profileCreative);
    }

    @Transactional
    public void deleteService(String userId, Long cardId) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        CreativeSeeksBusiness creativeSeeksBusiness = creativeSeeksBusinessRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not belong to the current user"));

        creativeSeeksBusinessRepository.delete(creativeSeeksBusiness);
    }

    private CreativeSeeksBusiness saveOrUpdateCreativeSeeksBusiness(CreativeSeeksBusinessDTO dto, CreativeSeeksBusiness entity, ProfileCreative profileCreative) {
        // Omit the null properties in copy
        handleAttributesUtility.copyNonNullProperties(dto, entity);

        // ATTENTION, HERE WE DO NOT VALID THE DATEs, IS ALREADY DONE IN VALIDATOR INSIDE THE DTO
        // Handle EducationType
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyEducationBackground()),
                entity::setIdentifyEducationBackground,
                educationTypeRepository);

        // Handle ExperienceLevel
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyExperienceLevel()),
                entity::setIdentifyExperienceLevel,
                experienceLevelRepository);

        // Handle ProfessionalRelationship
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyProfessionalRelationship()),
                entity::setIdentifyProfessionalRelationship,
                professionalRelationshipRepository);

        // Handle WorkPreference
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyWorkPreference()),
                entity::setIdentifyWorkPreference,
                workPreferenceRepository);

        // Handle TypeOfbusiness
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyBusinessType()),
                entity::setIdentifyBusinessType,
                typeOfBusinessRepository);

        // Assicura che la lista di località dell'entità non sia null.
        // Se è null, inizializzala prima di procedere.
        if (entity.getCreativeSeeksBusinessLocations() == null) {
            entity.setCreativeSeeksBusinessLocations(new HashSet<>());
        }
        // Handle CreativeSeeksBusinessLocation
        if (dto.getCreativeSeeksBusinessLocations() != null && !dto.getCreativeSeeksBusinessLocations().isEmpty()) {
            dto.getCreativeSeeksBusinessLocations().forEach(location -> {
                // Poiché hai già assicurato che getCreativeSeeksBusinessLocations() non sia null, questo stream non causerà NullPointerException.
                Optional<CreativeSeeksBusinessLocation> existingLocation = entity.getCreativeSeeksBusinessLocations().stream()
                        .filter(ct -> ct.getCity().equals(location.getCity()) && ct.getProvince().equals(location.getProvince()))
                        .findFirst();

                if (!existingLocation.isPresent()) { // Se non esiste, aggiungilo
                    CreativeSeeksBusinessLocation newLocation = new CreativeSeeksBusinessLocation();
                    BeanUtils.copyProperties(location, newLocation);
                    newLocation.setCreativeSeeksBusiness(entity); // Imposta il riferimento
                    entity.getCreativeSeeksBusinessLocations().add(newLocation); // Aggiungi alla collezione
                }
                // Se esiste, si presume che non sia necessario aggiornare
            });
        }

        entity.setProfileCreative(profileCreative);

        creativeSeeksBusinessRepository.save(entity);

        // Chiamate asincrone per aggiornare le coordinate
        if (dto.getCreativeSeeksBusinessLocations() != null) {
            entity.getCreativeSeeksBusinessLocations().forEach(location -> {
                asyncGeoService.updateCityTargetCoordinates(creativeSeeksBusinessLocationRepository, location.getId(), location.getCity(), location.getProvince());
            });
        }

        return entity;
    }
}

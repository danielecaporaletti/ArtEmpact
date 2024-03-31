package com.artempact.backend.artempact.controller.card.creativeSeeksCollaboration;

import com.artempact.backend.artempact.controller.HandleAttributesUtility;
import com.artempact.backend.artempact.dto.card.creativeSeeksCollaboration.CreativeSeeksCollaborationDTO;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.junction.CreativeSeeksCollaborationLocation;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.geoService.AsyncGeoService;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksCollaboration.CreativeSeeksCollaborationRepository;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksCollaboration.junction.CreativeSeeksCollaborationLocationRepository;
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
public class CreativeSeeksCollaborationService {

    private final HandleAttributesUtility handleAttributesUtility;
    private final AsyncGeoService asyncGeoService;
    private final ProfileCreativeRepository profileCreativeRepository;
    private final EducationTypeRepository educationTypeRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final ProfessionalRelationshipRepository professionalRelationshipRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private final CreativeSeeksCollaborationRepository creativeSeeksCollaborationRepository;
    private final CreativeSeeksCollaborationLocationRepository creativeSeeksCollaborationLocationRepository;

    @Transactional
    public Set<CreativeSeeksCollaboration> getService(String userId) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        return profileCreative.getCreativeSeeksCollaborations();
    }

    @Transactional
    public CreativeSeeksCollaboration postService(String userId, CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        CreativeSeeksCollaboration creativeSeeksCollaboration = new CreativeSeeksCollaboration();

        return saveOrUpdateCreativeSeeksCollaboration(creativeSeeksCollaborationDTO, creativeSeeksCollaboration, profileCreative);
    }

    @Transactional
    public CreativeSeeksCollaboration patchService(String userId, Long cardId, CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        CreativeSeeksCollaboration creativeSeeksCollaboration = creativeSeeksCollaborationRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not belong to the current user"));

        return saveOrUpdateCreativeSeeksCollaboration(creativeSeeksCollaborationDTO, creativeSeeksCollaboration, profileCreative);
    }

    @Transactional
    public void deleteService(String userId, Long cardId) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        CreativeSeeksCollaboration creativeSeeksCollaboration  = creativeSeeksCollaborationRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not belong to the current user"));

        creativeSeeksCollaborationRepository.delete(creativeSeeksCollaboration);
    }

    private CreativeSeeksCollaboration saveOrUpdateCreativeSeeksCollaboration(CreativeSeeksCollaborationDTO dto, CreativeSeeksCollaboration entity, ProfileCreative profileCreative) {
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

        // Handle TypeOfCreative
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyCreativeType()),
                entity::setIdentifyCreativeType,
                typeOfCreativeRepository);

        // Assicura che la lista di località dell'entità non sia null.
        // Se è null, inizializzala prima di procedere.
        if (entity.getCreativeSeeksCollaborationLocations() == null) {
            entity.setCreativeSeeksCollaborationLocations(new HashSet<>());
        }
        // Handle CreativeSeeksCollaborationLocation
        if (dto.getCreativeSeeksCollaborationLocations() != null && !dto.getCreativeSeeksCollaborationLocations().isEmpty()) {
            dto.getCreativeSeeksCollaborationLocations().forEach(location -> {
                Optional<CreativeSeeksCollaborationLocation> existingLocation = entity.getCreativeSeeksCollaborationLocations().stream()
                        .filter(ct -> ct.getCity().equals(location.getCity()) && ct.getProvince().equals(location.getProvince()))
                        .findFirst();

                if (!existingLocation.isPresent()) { // Se non esiste, aggiungilo
                    CreativeSeeksCollaborationLocation newLocation = new CreativeSeeksCollaborationLocation();
                    BeanUtils.copyProperties(location, newLocation);
                    newLocation.setCreativeSeeksCollaboration(entity); // Imposta il riferimento
                    entity.getCreativeSeeksCollaborationLocations().add(newLocation); // Aggiungi alla collezione
                }
                // Se esiste, si presume che non sia necessario aggiornare
            });
        }

        entity.setProfileCreative(profileCreative);

        creativeSeeksCollaborationRepository.save(entity);

        // Chiamate asincrone per aggiornare le coordinate
        if (dto.getCreativeSeeksCollaborationLocations() != null) {
            entity.getCreativeSeeksCollaborationLocations().forEach(location -> {
                asyncGeoService.updateCityTargetCoordinates(creativeSeeksCollaborationLocationRepository, location.getId(), location.getCity(), location.getProvince());
            });
        }

        return entity;
    }
}

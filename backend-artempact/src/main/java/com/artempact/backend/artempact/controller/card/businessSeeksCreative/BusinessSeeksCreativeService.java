package com.artempact.backend.artempact.controller.card.businessSeeksCreative;

import com.artempact.backend.artempact.controller.HandleAttributesUtility;
import com.artempact.backend.artempact.dto.card.businessSeeksCreative.BusinessSeeksCreativeDTO;
import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.artempact.backend.artempact.geoService.AsyncGeoService;
import com.artempact.backend.artempact.repository.repository.card.businessSeeksCreative.BusinessSeeksCreativeRepository;
import com.artempact.backend.artempact.repository.repository.lookup.*;
import com.artempact.backend.artempact.repository.repository.profile.business.ProfileBusinessRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BusinessSeeksCreativeService {

    private final HandleAttributesUtility handleAttributesUtility;
    private final AsyncGeoService asyncGeoService;
    private final ProfileBusinessRepository profileBusinessRepository;
    private final EducationTypeRepository educationTypeRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final ProfessionalRelationshipRepository professionalRelationshipRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private final BusinessSeeksCreativeRepository businessSeeksCreativeRepository;

    @Transactional
    public Set<BusinessSeeksCreative> getService(String userId) {
        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileBusiness not found for id: " + userId));

        return profileBusiness.getBusinessSeeksCreatives();
    }

    @Transactional
    public BusinessSeeksCreative postService(String userId, BusinessSeeksCreativeDTO businessSeeksCreativeDTO) {
        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileBusiness not found for id: " + userId));

        BusinessSeeksCreative businessSeeksCreative = new BusinessSeeksCreative();

        return saveOrUpdateBusinessSeeksCreative(businessSeeksCreativeDTO, businessSeeksCreative , profileBusiness);
    }

    @Transactional
    public BusinessSeeksCreative patchService(String userId, Long cardId, BusinessSeeksCreativeDTO businessSeeksCreativeDTO) {
        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileBusiness not found for id: " + userId));

        BusinessSeeksCreative businessSeeksCreative = businessSeeksCreativeRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not belong to the current user"));

        return saveOrUpdateBusinessSeeksCreative(businessSeeksCreativeDTO, businessSeeksCreative , profileBusiness);
    }

    @Transactional
    public void deleteService(String userId, Long cardId) {
        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileBusiness not found for id: " + userId));

        BusinessSeeksCreative businessSeeksCreative = businessSeeksCreativeRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not belong to the current user"));

        businessSeeksCreativeRepository.delete(businessSeeksCreative);
    }

    private BusinessSeeksCreative saveOrUpdateBusinessSeeksCreative(BusinessSeeksCreativeDTO dto, BusinessSeeksCreative entity, ProfileBusiness profileBusiness) {
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

        // Handle typeOfCreative
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyCreativeType()),
                entity::setIdentifyCreativeType,
                typeOfCreativeRepository);

        entity.setProfileBusiness(profileBusiness);

        businessSeeksCreativeRepository.save(entity);

        return entity;
    }
}

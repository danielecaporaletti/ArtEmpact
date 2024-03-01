package com.artempact.backend.mysqlArtempact.validator.card.BusinessSeeksCreative;

import com.artempact.backend.mysqlArtempact.dto.card.businessSeeksCreative.BusinessSeeksCreativeDTO;
import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.ProfileBusinessDTO;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.*;
import com.artempact.backend.mysqlArtempact.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class BusinessSeeksCreativeDTOValidator extends CommonValidator {

    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private ProfessionalRelationshipRepository professionalRelationshipRepository;
    @Autowired
    private TypeOfCreativeRepository typeOfCreativeRepository;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessSeeksCreativeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusinessSeeksCreativeDTO dto = (BusinessSeeksCreativeDTO) target;

        // Validate title
        validateStringField(dto.getTitle(), "title", 55, errors, null);
        // Validate description
        validateStringField(dto.getDescription(), "description", 280, errors, null);
        // Validate minProjectBudget
        validatePositiveField(dto.getMinProjectBudget(), "minProjectBudget", errors);
        // Validate maxProjectBudget
        validatePositiveField(dto.getMaxProjectBudget(), "maxProjectBudget", errors);
        // Validate cardColor
        validateShortField(dto.getCardColor(), "cardColor", (short) 0, (short) 99, errors);
//        validateRGBField(dto.getCardColor(), "cardColor", 7, 7, errors, "^#[0-9A-Fa-f]{6}$", "Format: #RRGGBB");
        // Validate educationalBackground
        validateShortKeyField(dto.getEducationalBackground(), "educationalBackground", educationTypeRepository.findAllIds(), errors);
        // Validate experienceLevel
        validateShortKeyField(dto.getExperienceLevel(), "experienceLevel", experienceLevelRepository.findAllIds(), errors);
        // Validate professionalRelationship
        validateShortKeyField(dto.getProfessionalRelationship(), "professionalRelationship", professionalRelationshipRepository.findAllIds(), errors);
        // Validate companyVisionMission
        validateStringField(dto.getCompanyVisionMission(), "companyVisionMission", 280, errors, null);
        // Validate identifyCreativeType
        validateShortKeyField(dto.getIdentifyCreativeType(), "identifyCreativeType", typeOfCreativeRepository.findAllIds(), errors);
        // Validate workPreference
        validateShortKeyField(dto.getWorkPreference(), "workPreference", workPreferenceRepository.findAllIds(), errors);
        // Validate S3 files
        validateWrongField(dto.getVideo1(), "video1", errors);
    }
}

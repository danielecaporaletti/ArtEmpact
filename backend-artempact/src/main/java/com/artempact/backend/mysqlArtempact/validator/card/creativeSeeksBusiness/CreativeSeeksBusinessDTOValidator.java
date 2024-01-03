package com.artempact.backend.mysqlArtempact.validator.card.creativeSeeksBusiness;

import com.artempact.backend.mysqlArtempact.dto.card.businessSeeksCreative.BusinessSeeksCreativeDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.CreativeSeeksBusinessDTO;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.ExperienceLevelRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.ProfessionalRelationshipRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CreativeSeeksBusinessDTOValidator extends CommonValidator {

    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private ProfessionalRelationshipRepository professionalRelationshipRepository;
    @Autowired
    private TypeOfBusinessRepository typeOfBusinessRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreativeSeeksBusinessDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreativeSeeksBusinessDTO dto = (CreativeSeeksBusinessDTO) target;

        // Validate title
        validateStringField(dto.getTitle(), "title", 55, errors, null);
        // Validate description
        validateStringField(dto.getDescription(), "description", 280, errors, null);
        // Validate minProjectBudget
        validatePositiveField(dto.getMinProjectBudget(), "minProjectBudget", errors);
        // Validate maxProjectBudget
        validatePositiveField(dto.getMaxProjectBudget(), "maxProjectBudget", errors);
        // Validate description
        validateRGBField(dto.getCardColor(), "cardColor", 7, 7, errors, "^#[0-9A-Fa-f]{6}$", "Format: #RRGGBB");
        // Validate educationalBackground
        validateShortKeyField(dto.getEducationalBackground(), "educationalBackground", educationTypeRepository.findAllIds(), errors);
        // Validate experienceLevel
        validateShortKeyField(dto.getExperienceLevel(), "experienceLevel", experienceLevelRepository.findAllIds(), errors);
        // Validate professionalRelationship
        validateShortKeyField(dto.getProfessionalRelationship(), "professionalRelationship", professionalRelationshipRepository.findAllIds(), errors);
        // Validate positionDescription
        validateStringField(dto.getPositionDescription(), "personalVisionMission", 280, errors, null);
        // Validate identifyCreativeType
        validateShortKeyField(dto.getIdentifyBusinesType(), "identifyBusinesType", typeOfBusinessRepository.findAllIds(), errors);
        // Validate jobSearchLocationCards
        validateSetLocation(dto.getJobSearchLocationCards(), errors, "jobSearchLocationCards");
    }
}

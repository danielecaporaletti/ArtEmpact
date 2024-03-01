package com.artempact.backend.mysqlArtempact.validator.profile.profileCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.ProfileCreativeDTO;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.validator.CommonValidator;
import com.artempact.backend.mysqlGeographic.reposity.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ProfileCreativeDTOValidator extends CommonValidator {

    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private TypeOfCreativeRepository typeOfCreativeRepository;
    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileCreativeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileCreativeDTO dto = (ProfileCreativeDTO) target;

        // Validate email
        validateStringField(dto.getEmail(), "email", 254, errors, "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
        // Validate phone
        validateStringField(dto.getPhone(), "phone", 20, errors, "^\\+(?:[0-9] ?){6,14}[0-9]$");
        // Validate name
        validateStringField(dto.getName(), "name", 50, errors, null);
        // Validate surname
        validateStringField(dto.getSurname(), "surname", 50, errors, null);
        // Validate date of birth as a string
        validateDateOfBirth(dto.getDob(), errors);
        // Validate locality
        validateLocality(dto.getLocality(), errors);
        // Validate description
        validateStringField(dto.getDescription(), "description", 280, errors, null);
        // Validate maxDistance
        validateShortField(dto.getMaxDistance(), "maxDistance", (short) 0, (short) 1000, errors);
        // Validate profileCompletionPercentage
        validateShortField(dto.getProfileCompletionPercentage(), "profileCompletionPercentage", (short) 0, (short) 100, errors);
        // Validate creativeName
        validateStringField(dto.getCreativeName(), "creativeName", 100, errors, null);
        // Validate typeOfCreative
        validateShortKeyField(dto.getTypeOfCreative(), "typeOfCreative", typeOfCreativeRepository.findAllIds(), errors);
        // Validate sectorOfCompetence
        validateStringField(dto.getSectorOfCompetence(), "sectorOfCompetence", 100, errors, null);
        // Validate educationType
        validateShortKeyField(dto.getEducationType(), "educationType", educationTypeRepository.findAllIds(), errors);
        // Validate finalYearOfEducation
        validateShortField(dto.getFinalYearOfEducation(), "finalYearOfEducation", (short) 1000, (short) 9000, errors);
        // Validate workPreference
        validateShortKeyField(dto.getWorkPreference(), "workPreference", workPreferenceRepository.findAllIds(), errors);
        // Validate S3 files
        validateWrongField(dto.getPhoto1(), "photo1", errors);
        validateWrongField(dto.getPhoto2(), "photo2", errors);
        validateWrongField(dto.getPhoto3(), "photo3", errors);
        validateWrongField(dto.getPhotoPremium1(), "photoPremium1", errors);
        validateWrongField(dto.getPhotoPremium2(), "photoPremium2", errors);
        validateWrongField(dto.getDocument1(), "document1", errors);
        // Validate jobSearchLocations
        validateSetLocation(dto.getJobSearchLocations(), errors, "jobSearchLocations");
    }
}

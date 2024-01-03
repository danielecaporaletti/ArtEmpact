package com.artempact.backend.mysqlArtempact.validator.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.ProfileBusinessDTO;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ProfileBusinessDTOValidator extends CommonValidator {

    @Autowired
    private TypeOfBusinessRepository typeOfBusinessRepository;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileBusinessDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileBusinessDTO dto = (ProfileBusinessDTO) target;

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
        // Validate businessName
        validateStringField(dto.getBusinessName(), "businessName", 100, errors, null);
        // Validate typeOfBusiness
        validateShortKeyField(dto.getTypeOfBusiness(), "typeOfBusiness", typeOfBusinessRepository.findAllIds(), errors);
        // Validate workPreference
        validateShortKeyField(dto.getWorkPreference(), "workPreference", workPreferenceRepository.findAllIds(), errors);
        // Validate photosBusiness
        // Validate creativeSearchLocations
        validateSetLocation(dto.getCreativeSearchLocations(), errors, "creativeSearchLocations");
    }

}

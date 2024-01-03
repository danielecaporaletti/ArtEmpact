package com.artempact.backend.mysqlArtempact.validator.profile.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.projectCreative.ProjectCreativeDTO;
import com.artempact.backend.mysqlArtempact.validator.CommonValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ProjectCreativeDTOValidator extends CommonValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectCreativeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProjectCreativeDTO dto = (ProjectCreativeDTO) target;

        // Validate name
        validateStringField(dto.getName(), "name", 50, errors, null);
        // Validate year
        validateShortField(dto.getYear(), "year", (short) 1000, (short) 9000, errors);
        // Validate type
        validateStringField(dto.getType(), "type", 100, errors, null);
        // Validate description
        validateStringField(dto.getDescription(), "description", 280, errors, null);
        // Validate customer
        validateStringField(dto.getCustomer(), "customer", 50, errors, null);
        // Validate link
        validateStringField(dto.getLink(), "link", 1000, errors, null);
    }
}

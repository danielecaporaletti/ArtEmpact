package com.artempact.backend.artempact.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearConstraintValidator implements ConstraintValidator<ValidYear, Short> {

    private int min;

    @Override
    public void initialize(ValidYear constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        int currentYear = Year.now().getValue();
        return value >= min && value <= currentYear;
    }
}

package com.artempact.backend.mysqlArtempact.controller.service.validation;

import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ValidationUtils  {

    /**
     * Validates a string field for non-null, non-blank, and within maximum length.
     *
     * @param value The value to validate.
     * @param maxLength The maximum length of the string.
     * @return String containing the result of the validation.
     */
    public static String validateStringField(Object value, int maxLength) {
        // Controlla se il valore nell'oggetto body è una stringa, sia piena, non sia piu lunga di maxLenght
        if (!(value instanceof String) || ((String) value).isBlank() || ((String) value).length() > maxLength) {
            throw new ValidationException("Invalid string field: " + value);
        }
        return (String) value;
    }

    /**
     * Validates a Short field for non-null, non-blank, and within maximum length.
     *
     * @param value The value to validate.
     * @param min The minimum of the number
     * @param max The maximum length of the string.
     * @return Short containing the result of the validation.
     */

    public static Short  validateShortField(Object value, short min, short max) {
        // Controlla se il valore nell'oggetto body è un numero o va bene anche una stringa, e controlla che sia tra due numeri
        if (!(value instanceof Number || value instanceof String)) {
            throw new ValidationException("Value is not a number or string for short field validation");
        }
        try {
            short shortValue = Short.parseShort(value.toString());
            if (shortValue >= min && shortValue <= max) {
                return shortValue;
            } else {
                throw new ValidationException("Short value out of range for field");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid format for short field");
        }
    }

    /**
     * Validates a LocalDate field for non-null, non-blank, and if the date is not in the future and if the user is adult.
     *
     * @param value The value to validate.
     * @return ValidationResult containing the result of the validation.
     */
    public static LocalDate  validateDobField(Object value) {
        // Controlla se il valore nell'oggetto body è una stringa
        if (!(value instanceof String)) {
            throw new ValidationException("Value is not a string for LocalDate field validation");
        }

        try {
            LocalDate dateValue = LocalDate.parse(value.toString());
            LocalDate today = LocalDate.now();
            LocalDate eighteenYearsAgo = today.minusYears(18);
            // Check if the date is not in the future and the user is at least 18 years old
            if (!dateValue.isAfter(today) && dateValue.isBefore(eighteenYearsAgo)) {
                return dateValue;
            } else {
                throw new ValidationException("Date is either in the future or the person is not an adult");
            }
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid format for LocalDate field");
        }
    }

    /**
     * Validates that the provided object is a list of strings, and each string does not exceed a specified maximum length.
     * This method checks if the given object is an instance of List<?> and verifies that every element in the list is a string
     * not exceeding the maxLength.
     *
     * @param value The object to be validated, expected to be a List of String objects.
     * @param maxLength The maximum allowed length of each string in the list.
     * @return A List of Strings if validation is successful.
     * @throws ValidationException if the value is not a list, contains non-string elements, or any string exceeds maxLength.
     */
    public static List<String> validateStringListField(Object value, int maxLength) {
        if (!(value instanceof List<?>)) {
            throw new ValidationException("Value is not a list for string list field validation");
        }
        List<?> list = (List<?>) value;
        for (Object item : list) {
            if (!(item instanceof String)) {
                throw new ValidationException("List contains non-string elements");
            }
            ValidationUtils.validateStringField(item, maxLength); // Validates each string in the list
        }
        return (List<String>) list;
    }

    /**
     * Validates that a given ID (as an Object) is present in a list of valid IDs.
     * The method first converts the Object to a Short, then checks if this Short
     * is contained within the provided list of valid IDs.
     *
     * @param value The value to be validated as an ID.
     * @param validIds The list of valid IDs against which to check the value.
     * @return The validated ID if it is within the list of valid IDs.
     * @throws ValidationException if the value is not a number, not convertible to Short,
     *                             or not present in the list of valid IDs.
     */
    public static Short checkIdInShortList(Object value, List<Short> validIds) {
        if (!(value instanceof Number)) {
            throw new ValidationException("Provided value is not a number");
        }

        Short id;
        try {
            id = ((Number) value).shortValue();
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid format for ID");
        }

        if (!validIds.contains(id)) {
            throw new ValidationException("ID " + id + " is not in the list of valid IDs");
        }

        return id;
    }

    /**
     * Validates that the provided object is a list of maps representing job search locations,
     * where each map contains a 'city' and a 'province' as strings. This method ensures that each element
     * in the list is a map and that the 'city' and 'province' values in the map are strings not exceeding
     * their respective maximum lengths.
     *
     * @param value The object to be validated, expected to be a List of Map objects.
     * @param cityMaxLength The maximum allowed length of the 'city' string in each map.
     * @param provinceMaxLength The maximum allowed length of the 'province' string in each map.
     * @return A List of Maps with validated 'city' and 'province' if validation is successful.
     * @throws ValidationException if the value is not a list, contains non-map elements, or any 'city'/'province'
     *         string does not meet the length criteria or is not a string.
     */
    public static List<Map<String, Object>> validateJobSearchLocationsField(Object value, int cityMaxLength, int provinceMaxLength) {
        if (!(value instanceof List<?>)) {
            throw new ValidationException("Value is not a list for job search locations field validation");
        }

        List<?> list = (List<?>) value;
        List<Map<String, Object>> validatedList = new ArrayList<>();

        for (Object item : list) {
            if (!(item instanceof Map<?, ?>)) {
                throw new ValidationException("List contains non-map elements");
            }

            Map<?, ?> map = (Map<?, ?>) item;
            Object city = map.get("city");
            Object province = map.get("province");

            if (!(city instanceof String) || !(province instanceof String)) {
                throw new ValidationException("Map elements 'city' or 'province' are not of type String");
            }

            // Validate 'city' and 'province' using the existing validation method
            String validatedCity = ValidationUtils.validateStringField((String) city, cityMaxLength);
            String validatedProvince = ValidationUtils.validateStringField((String) province, provinceMaxLength);

            // Add the validated map to the list
            validatedList.add(Map.of("city", validatedCity, "province", validatedProvince));
        }

        return validatedList;
    }


}

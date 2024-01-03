package com.artempact.backend.mysqlArtempact.validator;

import com.artempact.backend.mysqlArtempact.dto.profile.ProfileDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import com.artempact.backend.mysqlGeographic.reposity.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

public abstract class CommonValidator implements Validator {

    @Autowired
    protected ComuneRepository comuneRepository;

    protected void validateStringField(String value, String fieldName, int maxLength, Errors errors, String regex) {
        if (value != null) {
            value = value.trim();
            if (value.isEmpty()) {
                errors.rejectValue(fieldName, fieldName + ".empty", "The " + fieldName + " must not be empty.");
            } else {
                if (value.length() > maxLength) {
                    errors.rejectValue(fieldName, fieldName + ".length.error", new Object[]{maxLength}, "The " + fieldName + " must not exceed " + maxLength + " characters.");
                }
                if (regex != null && !value.matches(regex)) {
                    errors.rejectValue(fieldName, fieldName + ".format.error", "The " + fieldName + " format is invalid.");
                }
            }
        }
    }

    protected void validateShortField(String value, String fieldName, Short min, Short max, Errors errors) {
        if (value != null) {
            if (!value.trim().isEmpty()) {
                Short shortvalue = null;
                try {
                    shortvalue = Short.parseShort(value.trim());
                    if (shortvalue < min) {
                        errors.rejectValue(fieldName, fieldName + ".min.error", new Object[]{min}, "The " + fieldName + " must be grower than " + min);
                    } else if (shortvalue > max) {
                        errors.rejectValue(fieldName, fieldName + ".max.error", new Object[]{min}, "The " + fieldName + " must be lower than " + max);
                    }
                } catch (NumberFormatException e) {
                    errors.rejectValue(fieldName, fieldName + ".format", "The " + fieldName + " is not a number.");
                }
            } else {
                errors.rejectValue(fieldName, fieldName + ".empty", "The " + fieldName + " must not be empty.");
            }
        }
    }

    protected void validateShortKeyField(String value, String fieldName, List<Short> validkeys, Errors errors) {
        if (value != null) {
            if (!value.trim().isEmpty()) {
                Short shortvalue = null;
                try {
                    shortvalue = Short.parseShort(value.trim());
                    if (! validkeys.contains(shortvalue)) {
                        errors.rejectValue(fieldName, fieldName + ".invalid", "The provided " + fieldName + " is invalid.");
                    }
                } catch (NumberFormatException e) {
                    errors.rejectValue(fieldName, fieldName + ".format", "The " + fieldName + " is not a number.");
                }
            } else {
                errors.rejectValue(fieldName, fieldName + ".empty", "The " + fieldName + " must not be empty.");
            }
        }
    }

    protected void validateDateOfBirth(String dob, Errors errors) {
        if (dob != null) {
            dob = dob.trim();
            if (!dob.isEmpty()) {
                try {
                    LocalDate parsedDob = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
                    if (parsedDob.isAfter(LocalDate.now())) {
                        errors.rejectValue("dob", "dob.future", "The date of birth cannot be in the future.");
                    } else if (Period.between(parsedDob, LocalDate.now()).getYears() < 18) {
                        errors.rejectValue("dob", "dob.age", "The age must be over 18 years.");
                    }
                } catch (DateTimeParseException e) {
                    errors.rejectValue("dob", "dob.format", "The date of birth must be in the format yyyy-MM-dd.");
                }
            } else {
                errors.rejectValue("dob", "dob.empty", "The date of birth must not be empty.");
            }
        }
    }

    protected void validateLocality(LocationInterface locality, Errors errors) {
        if (locality != null) {
            boolean valid = true;
            String city = locality.getCity();
            String province = locality.getProvince();

            // Validazione di city
            if (city == null || city.trim().isEmpty()) {
                errors.rejectValue("city", "city.empty", "The city must not be empty.");
                valid = false;
            } else if (city.trim().length() > 100) {
                errors.rejectValue("city", "city.length.error", "The city must not exceed 100 characters.");
                valid = false;
            }

            // Validazione di province
            if (province == null || province.trim().isEmpty()) {
                errors.rejectValue("province", "province.empty", "The province must not be empty.");
                valid = false;
            } else if (province.trim().length() > 4) {
                errors.rejectValue("province", "province.length.error", "The province must not exceed 4 characters.");
                valid = false;
            }

            if (valid) {
                // Controlla la città e la provincia nel database e imposta latitudine e longitudine
                checkAndSetLatLon(locality, errors);
            }
        }
    }

    protected void checkAndSetLatLon(LocationInterface locality, Errors errors) {
        List<Object[]> latLon = comuneRepository.findLatLonByCittaAndProvincia(locality.getCity().trim(), locality.getProvince().trim());
        if (!latLon.isEmpty()) {
            Object[] latLonValues = latLon.get(0);
            BigDecimal lat = (BigDecimal) latLonValues[0];
            BigDecimal lon = (BigDecimal) latLonValues[1];

            locality.setLat(lat.doubleValue());
            locality.setLon(lon.doubleValue());
        } else {
            errors.rejectValue("locality", "locality.notfound", "City and province not found in the database.");
        }
    }

    protected  void validateSetLocation(Set<? extends LocationInterface> locations, Errors errors, String pathPrefix) {
        if (locations == null) {
            return;
        }
        int i = 0;
        for (LocationInterface location : locations) {
            boolean valid = true;
            String city = location.getCity();
            String province = location.getProvince();

            // Validate city
            if (city == null || city.trim().isEmpty()) {
                errors.rejectValue(pathPrefix + "[" + i + "].city", pathPrefix + ".city.empty", "The city must not be empty.");
                valid = false;
            } else if (city.trim().length() > 100) {
                errors.rejectValue(pathPrefix + "[" + i + "].city", pathPrefix + ".city.length.error", "The city must not exceed 100 characters.");
                valid = false;
            }

            // Validate province
            if (province == null || province.trim().isEmpty()) {
                errors.rejectValue(pathPrefix + "[" + i + "].province", pathPrefix + ".province.empty", "The province must not be empty.");
                valid = false;
            } else if (province.trim().length() > 4) {
                errors.rejectValue(pathPrefix + "[" + i + "].province", pathPrefix + ".province.length.error", "The province must not exceed 4 characters.");
                valid = false;
            }

            // Check for city and province in the database
            if (valid) {
                List<Object[]> latLon = comuneRepository.findLatLonByCittaAndProvincia(city.trim(), province.trim());
                if (latLon.isEmpty()) {
                    errors.rejectValue(pathPrefix + "[" + i + "]", pathPrefix + ".notfound", "City and province not found in the database.");
                } else {
                    Object[] latLonValues = latLon.get(0);
                    BigDecimal lat = (BigDecimal) latLonValues[0];
                    BigDecimal lon = (BigDecimal) latLonValues[1];

                    location.setLat(lat.doubleValue());
                    location.setLon(lon.doubleValue());
                }
            }
            i++;
        }
    }

    protected void validatePositiveField(String value, String fieldName, Errors errors) {
        if (value != null) {
            if (!value.trim().isEmpty()) {
                Integer intvalue = null;
                try {
                    intvalue = Integer.parseInt(value.trim());
                    if (intvalue < 0) {
                        errors.rejectValue(fieldName, fieldName + ".min.error", new Object[]{0}, "The " + fieldName + " must be grower than " + 0);
                    }
                } catch (NumberFormatException e) {
                    errors.rejectValue(fieldName, fieldName + ".format", "The " + fieldName + " is not a number.");
                }
            } else {
                errors.rejectValue(fieldName, fieldName + ".empty", "The " + fieldName + " must not be empty.");
            }
        }
    }

    protected void validateRGBField(String value, String fieldName, Integer min, Integer max, Errors errors, String regex, String formatErrorMessage) {
        if (value != null) {
            value = value.trim();
            if (value.isEmpty()) {
                errors.rejectValue(fieldName, fieldName + ".empty", "The " + fieldName + " must not be empty.");
            } else {
                if (value.length() > max) {
                    errors.rejectValue(fieldName, fieldName + ".length.error", new Object[]{max}, "The " + fieldName + " must not exceed " + max + " characters." + (formatErrorMessage != null ? " " + formatErrorMessage : ""));
                }
                if (value.length() < min) {
                    errors.rejectValue(fieldName, fieldName + ".length.error", new Object[]{min}, "The " + fieldName + " must be at least " + min + " characters." + (formatErrorMessage != null ? " " + formatErrorMessage : ""));
                }
                if (regex != null && !value.matches(regex)) {
                    errors.rejectValue(fieldName, fieldName + ".format.error", "The " + fieldName + " format is invalid." + (formatErrorMessage != null ? " " + formatErrorMessage : ""));
                }
            }
        }
    }


}

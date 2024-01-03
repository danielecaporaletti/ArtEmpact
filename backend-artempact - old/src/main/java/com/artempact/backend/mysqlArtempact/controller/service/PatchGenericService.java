package com.artempact.backend.mysqlArtempact.controller.service;

import com.artempact.backend.mysqlArtempact.controller.service.validation.ValidationUtils;
import com.artempact.backend.mysqlArtempact.entity.profile.Profile;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfCreativeRepository;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class PatchGenericService {

    private final ProfileCreativeRepository profileCreativeRepository;
    private final ProfileCreativeControllerService profileCreativeControllerService;
    private final ProfileBusinessControllerService profileBusinessControllerService;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private final TypeOfBusinessRepository typeOfBusinessRepository;
    private final EducationTypeRepository educationTypeRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final Validator validator;
    private final ProjectCreativeControllerService projectCreativeControllerService;

    public PatchGenericService(ProfileCreativeRepository profileCreativeRepository, ProfileCreativeControllerService profileCreativeControllerService, ProfileBusinessControllerService profileBusinessControllerService, TypeOfCreativeRepository typeOfCreativeRepository, TypeOfBusinessRepository typeOfBusinessRepository, EducationTypeRepository educationTypeRepository, WorkPreferenceRepository workPreferenceRepository, Validator validator, ProjectCreativeControllerService projectCreativeControllerService) {
        this.profileCreativeRepository = profileCreativeRepository;
        this.profileCreativeControllerService = profileCreativeControllerService;
        this.profileBusinessControllerService = profileBusinessControllerService;
        this.typeOfCreativeRepository = typeOfCreativeRepository;
        this.typeOfBusinessRepository = typeOfBusinessRepository;
        this.educationTypeRepository = educationTypeRepository;
        this.workPreferenceRepository = workPreferenceRepository;
        this.validator = validator;
        this.projectCreativeControllerService = projectCreativeControllerService;
    }

    // Inietta tutto in Spring

    public <T> ResponseEntity<?> updateEntity(T entity, Map<String, Object> updates, Class<T> entityType, Function<T, T> saveFunction, JwtAuthenticationToken auth) {
        BindException errors = new BindException(entity, entityType.getSimpleName().toLowerCase());
        List<String> invalidFields = new ArrayList<>();

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(entityType, key);
            if (field != null) {
                updateFieldGeneric(entity, field, key, value, errors, auth);
            } else {
                invalidFields.add(key);
            }
        });

        if (!invalidFields.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid fields: " + invalidFields);
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        T updatedEntity = saveFunction.apply(entity);
        return ResponseEntity.ok(updatedEntity);
    }

    private <T> void updateFieldGeneric(T entity, Field field, String fieldName, Object value, Errors errors, JwtAuthenticationToken auth) {
        Jwt jwtToken = auth.getToken();
        Map<String, Object> realmAccess = jwtToken.getClaim("realm_access");
        String role = null;

        if (realmAccess != null) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            if (roles != null) {
                if (roles.contains("creative")) {
                    role = "creative";
                } else if (roles.contains("business")) {
                    role = "business";
                }
            }
        }


        try {
            // Make the field accessible even if it is private
            field.setAccessible(true);

            switch (fieldName) {
//--------------PROFILE FIELDS---------------------------------------------------------------------------------
                case "email":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 254));
                    break;
                case "phone":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 20));
                    break;
                case "name":
                case "surname":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 50));
                    break;
                case "description":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 280));
                    break;
                case "dob":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateDobField(value));
                    break;
                case "maxDistanceKm":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateShortField(value, (short)0, (short)1000));
                    break;
                case "profileCompletionPercentage":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateShortField(value, (short)0, (short)100));
                    break;
                case "locality":
                    Map<String, Object> localityMap = (Map<String, Object>) value;
                    String city = (String) localityMap.get("city");
                    String province = (String) localityMap.get("province");
                    Profile.Locality newLocality = null;

                    if (role.equals("creative")) {
                        newLocality = profileCreativeControllerService.updateCreativeLocality(
                                ValidationUtils.validateStringField(city, 100),
                                ValidationUtils.validateStringField(province, 4)
                        );
                    }
                    if (role.equals("business")) {
                        newLocality = profileBusinessControllerService.updateBusinessLocality(
                                ValidationUtils.validateStringField(city, 100),
                                ValidationUtils.validateStringField(province, 4)
                        );
                    }

                    ReflectionUtils.setField(field, entity, newLocality);
                    break;
                case "workPreference":
                    List<Short> workPreferenceCreRepositoryAllIds = workPreferenceRepository.findAllIds();
                    Short workPreferenceId = ValidationUtils.checkIdInShortList(value, workPreferenceCreRepositoryAllIds);
                    if (role.equals("creative")) {
                        profileCreativeControllerService.handleWorkPreferenceUpdate((ProfileCreative)entity, workPreferenceId);
                    }
                    if (role.equals("business")) {
                        profileBusinessControllerService.handleWorkPreferenceUpdate((ProfileBusiness)entity, workPreferenceId);
                    }
                    break;
//--------------CREATIVE PROJECT FIELDS---------------------------------------------------------------------------------
                case "customer":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 50));
                    break;
                case "type":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 100));
                    break;
                case "link":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 1000));
                    break;
                case "year":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateShortField(value, (short) 1000, (short) 9000));
                    break;
                case "photoProjectCreatives":
                    List<String> projectPhotoLinks = ValidationUtils.validateStringListField(value, 1000);
                    projectCreativeControllerService.handlePhotoProjectCreativesUpdate((ProjectCreative)entity, projectPhotoLinks, errors);
                    break;
//--------------CREATIVE PROFILE FIELDS---------------------------------------------------------------------------------
                case "creativeName":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 100));
                    break;
                case "finalYearOfEducation":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateShortField(value, (short)1000, (short)9000));
                    break;
                case "typeOfCreative":
                    List<Short> typeOfCreativeRepositoryAllIds = typeOfCreativeRepository.findAllIds();
                    Short typeOfCreativeId = ValidationUtils.checkIdInShortList(value, typeOfCreativeRepositoryAllIds);
                    profileCreativeControllerService.handleTypeOfCreativeUpdate((ProfileCreative)entity, typeOfCreativeId);
                    break;
                case "educationType":
                    List<Short> educationTypeRepositoryAllIds = educationTypeRepository.findAllIds();
                    Short educationTypeId = ValidationUtils.checkIdInShortList(value, educationTypeRepositoryAllIds);
                    profileCreativeControllerService.handleEducationTypeUpdate((ProfileCreative)entity, educationTypeId);
                    break;
                case "photosCreatives":
                    List<String> profilePhotoCreLinks = ValidationUtils.validateStringListField(value, 1000);
                    profileCreativeControllerService.handlePhotoUpdate((ProfileCreative)entity, profilePhotoCreLinks);
                    break;
                case "jobSearchLocations":
                    List<Map<String, Object>> locationCreMaps = ValidationUtils.validateJobSearchLocationsField(value, 100, 4);
                    profileCreativeControllerService.handleJobSearchLocationsUpdate((ProfileCreative) entity, locationCreMaps);
                    break;
//--------------BUSINESS PROFILE FIELDS---------------------------------------------------------------------------------
                case "businessName":
                    ReflectionUtils.setField(field, entity, ValidationUtils.validateStringField(value, 100));
                    break;
                case "typeOfBusiness":
                    List<Short> typeOfBusinessRepositoryAllIds = typeOfBusinessRepository.findAllIds();
                    Short typeOfBusinessId = ValidationUtils.checkIdInShortList(value, typeOfBusinessRepositoryAllIds);
                    profileBusinessControllerService.handleTypeOfBusinessUpdate((ProfileBusiness)entity, typeOfBusinessId);
                    break;
                case "photoBusinesses":
                    List<String> profilePhotoBusLinks = ValidationUtils.validateStringListField(value, 1000);
                    profileBusinessControllerService.handlePhotoUpdate((ProfileBusiness)entity, profilePhotoBusLinks);
                    break;
                case "creativeSearchLocations":
                    List<Map<String, Object>> locationBusMaps = ValidationUtils.validateJobSearchLocationsField(value, 100, 4);
                    profileBusinessControllerService.handleJobSearchLocationsUpdate((ProfileBusiness) entity, locationBusMaps);
                    break;
                default:
                    errors.rejectValue(fieldName, "UnrecognizedField", "Unrecognized field: " + fieldName);
                    break;
            }
        } catch (ValidationException e) {
            errors.rejectValue(fieldName, "ValidationException", e.getMessage());
        } catch (IllegalArgumentException e) {
            errors.rejectValue(fieldName, "UpdateError", "Error updating field: " + fieldName);
        }
    }
}

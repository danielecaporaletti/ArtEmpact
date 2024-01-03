package com.artempact.backend.mysqlArtempact.controller.profileCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.controller.service.PatchGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/profile/creative")
public class ProfileCreativeController {

    private final ProfileCreativeRepository profileCreativeRepository;
    private final PatchGenericService patchGenericService;

    @Autowired
    public ProfileCreativeController(ProfileCreativeRepository profileCreativeRepository, PatchGenericService patchGenericService) {
        this.profileCreativeRepository = profileCreativeRepository;
        this.patchGenericService = patchGenericService;
    }

    // Ritorna il profilo dell'utente (creativo) corrente
    @GetMapping
    public ResponseEntity<ProfileCreative> getProfileCreative(JwtAuthenticationToken auth) {

        // Estrarre l'ID dell'utente corrente
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        Optional<ProfileCreative> profileCreative = profileCreativeRepository.findById(id);

        if (profileCreative.isPresent()) {
            return ResponseEntity.ok(profileCreative.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo PATCH per aggiornare parzialmente un profilo creativo
    @PatchMapping
    public ResponseEntity<?> patchProfileCreative(JwtAuthenticationToken auth, @RequestBody Map<String, Object> updates) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return profileCreativeRepository.findById(id)
                .map(profile -> patchGenericService.updateEntity(profile, updates, ProfileCreative.class, profileCreativeRepository::save, auth))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    @PatchMapping
    public ResponseEntity<?> patchProfileCreative(JwtAuthenticationToken auth,
                                                  @RequestBody Map<String, Object> updates) {
        // Extract the current user's ID from the authentication token
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Find the ProfileCreative by its ID
        return profileCreativeRepository.findById(id)
                .map(profile -> updateProfile(profile, updates))
                .orElseGet(() -> ResponseEntity.notFound().build()); // If the profile is not found, respond with Not Found status
    }

    private ResponseEntity<?> updateProfile(ProfileCreative profile, Map<String, Object> updates) {
        BindException errors = new BindException(profile, "profileCreative");
        List<String> invalidFields = new ArrayList<>();

        // Iterate over each field in the updates
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ProfileCreative.class, key);
                if (field != null) {
                    // Update the field if it exists in the ProfileCreative class
                    updateField(profile, field, key, value, errors);
                } else {
                    invalidFields.add(key); // Add the key to the list of invalid fields
                }
        });

        // If there are invalid fields, return a custom error response
        if (!invalidFields.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid fields: " + invalidFields);
        }

        // Continue with validation and saving the project as before
        if (!errors.hasErrors()) {
            validator.validate(profile, errors);
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        ProfileCreative profileCreative = profileCreativeRepository.save(profile);
        return ResponseEntity.ok(profileCreative);
    }

    private void updateField(ProfileCreative profile, Field field, String fieldName, Object value, Errors errors) {
        try {
            // Make the field accessible even if it is private
            field.setAccessible(true);

            switch (fieldName) {
                case "email":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateStringField(value, 254));
                    break;
                case "phone":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateStringField(value, 20));
                    break;
                case "name":
                case "surname":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateStringField(value, 50));
                    break;
                case "creativeName":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateStringField(value, 100));
                    break;
                case "description":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateStringField(value, 280));
                    break;
                case "dob":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateDobField(value));
                    break;
                case "finalYearOfEducation":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateShortField(value, (short)1000, (short)9000));
                    break;
                case "maxDistanceKm":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateShortField(value, (short)0, (short)1000));
                    break;
                case "profileCompletionPercentage":
                    ReflectionUtils.setField(field, profile, ValidationUtils.validateShortField(value, (short)0, (short)100));
                    break;
                case "locality":
                    Map<String, Object> localityMap = (Map<String, Object>) value;
                    String city = (String) localityMap.get("city");
                    String province = (String) localityMap.get("province");

                    ProfileCreative.Locality newLocality = profileCreativeControllerService.updateLocality(
                            ValidationUtils.validateStringField(city, 100),
                            ValidationUtils.validateStringField(province, 2)
                    );
                    ReflectionUtils.setField(field, profile, newLocality);
                    break;
                case "typeOfCreative":
                    List<Short> typeOfCreativeRepositoryAllIds = typeOfCreativeRepository.findAllIds();
                    Short typeOfCreativeId = ValidationUtils.checkIdInShortList(value, typeOfCreativeRepositoryAllIds);
                    profileCreativeControllerService.handleTypeOfCreativeUpdate(profile, typeOfCreativeId);
                    break;
                case "educationType":
                    List<Short> educationTypeRepositoryAllIds = educationTypeRepository.findAllIds();
                    Short educationTypeId = ValidationUtils.checkIdInShortList(value, educationTypeRepositoryAllIds);
                    profileCreativeControllerService.handleEducationTypeUpdate(profile, educationTypeId);
                    break;
                case "workPreference":
                    List<Short> workPreferenceRepositoryAllIds = workPreferenceRepository.findAllIds();
                    Short workPreferenceId = ValidationUtils.checkIdInShortList(value, workPreferenceRepositoryAllIds);
                    profileCreativeControllerService.handleWorkPreferenceUpdate(profile, workPreferenceId);
                    break;
                case "photos":
                    List<String> photoLinks = ValidationUtils.validateStringListField(value, 1000);
                    profileCreativeControllerService.handlePhotoUpdate(profile, photoLinks);
                    break;
                case "jobSearchLocations":
                    List<String> cityNames = ValidationUtils.validateStringListField(value, 100);
                    profileCreativeControllerService.handleJobSearchLocationsUpdate(profile, cityNames);
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
     */

}

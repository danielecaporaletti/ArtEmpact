package com.artempact.backend.mysqlArtempact.controller.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.projectCreative.ProjectCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.projectCreative.junctionTables.PhotoProjectCreativeRepository;
import com.artempact.backend.mysqlArtempact.controller.service.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project/creative")
public class ProjectCreativeController {

    private ProfileCreativeRepository profileCreativeRepository;
    private ProjectCreativeRepository projectCreativeRepository;
    private final PatchGenericService patchGenericService;
    private PhotoProjectCreativeRepository photoProjectCreativeRepository;
    private ProjectCreativeControllerService projectCreativeControllerService;
    private final Validator validator;

    public ProjectCreativeController(ProfileCreativeRepository profileCreativeRepository, ProjectCreativeRepository projectCreativeRepository, PatchGenericService patchGenericService, PhotoProjectCreativeRepository photoProjectCreativeRepository, ProjectCreativeControllerService projectCreativeControllerService, Validator validator) {
        this.profileCreativeRepository = profileCreativeRepository;
        this.projectCreativeRepository = projectCreativeRepository;
        this.patchGenericService = patchGenericService;
        this.photoProjectCreativeRepository = photoProjectCreativeRepository;
        this.projectCreativeControllerService = projectCreativeControllerService;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<ProjectCreative> createProjectCreative(JwtAuthenticationToken auth, @RequestBody ProjectCreative projectCreative) {

        // Estrarre l'ID dell'utente corrente
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Trova il ProfileCreative usando l'ID fornito
        ProfileCreative profileCreative = profileCreativeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfileCreative not found"));

        // Collega il ProjectCreative al ProfileCreative
        projectCreative.setProfileCreative(profileCreative);

        // Salva il nuovo ProjectCreative nel database
        ProjectCreative savedProjectCreative = projectCreativeRepository.save(projectCreative);

        return new ResponseEntity<>(savedProjectCreative, HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectCreativeId}")
    public ResponseEntity<?> deleteProjectCreative(JwtAuthenticationToken auth, @PathVariable Long projectCreativeId) {
        // Estrarre l'ID dell'utente corrente dal token di autenticazione
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        return projectCreativeRepository.findById(projectCreativeId)
                .map(project -> {
                    // Verifica che l'utente corrente sia il proprietario del progetto
                    if (project.getProfileCreative().getId().equals(userId)) {
                        projectCreativeRepository.delete(project);
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non autorizzato ad eliminare questo progetto");
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{projectCreativeId}")
    public ResponseEntity<?> patchProjectCreative(JwtAuthenticationToken auth, @RequestBody Map<String, Object> updates, @PathVariable Long projectCreativeId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return projectCreativeRepository.findById(projectCreativeId)
                .map(project -> {
                    if (project.getProfileCreative().getId().equals(userId)) {
                        return patchGenericService.updateEntity(project, updates, ProjectCreative.class, projectCreativeRepository::save, auth);
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non autorizzato a modificare questo progetto");
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

/*
    @PatchMapping("/{projectCreativeId}")
    public ResponseEntity<?> patchProjectCreative(JwtAuthenticationToken auth,
                                                  @RequestBody Map<String, Object> updates,
                                                  @PathVariable Long projectCreativeId) {
        // Extract the current user's ID from the authentication token
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Find the ProjectCreative by its ID
        return projectCreativeRepository.findById(projectCreativeId)
                .map(project -> {
                    // Check if the current user is the owner of the project
                    if (project.getProfileCreative().getId().equals(userId)) {
                        // If the user is the owner, proceed to update the project
                        return updateProject(project, updates);
                    } else {
                        // If the user is not the owner, respond with a Forbidden status
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized to modify this project");
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // If the project is not found, respond with Not Found status
    }

    private ResponseEntity<?> updateProject(ProjectCreative project, Map<String, Object> updates) {
        BindException errors = new BindException(project, "projectCreative");
        List<String> invalidFields = new ArrayList<>();

        // Iterate over each field in the updates
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ProjectCreative.class, key);
            if (field != null) {
                // Update the field if it exists in the ProfileCreative class
                updateField(project, field, key, value, errors);
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
            validator.validate(project, errors);
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        ProjectCreative updatedProject = projectCreativeRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    private void updateField(ProjectCreative project, Field field, String fieldName, Object value, Errors errors) {
        try {
            // Make the field accessible even if it is private
            field.setAccessible(true);

            switch (fieldName) {
                case "name":
                case "customer":
                    ReflectionUtils.setField(field, project, ValidationUtils.validateStringField(value, 50));
                    break;
                case "type":
                    ReflectionUtils.setField(field, project, ValidationUtils.validateStringField(value, 100));
                    break;
                case "description":
                    ReflectionUtils.setField(field, project, ValidationUtils.validateStringField(value, 280));
                    break;
                case "link":
                    ReflectionUtils.setField(field, project, ValidationUtils.validateStringField(value, 1000));
                    break;
                case "year":
                    ReflectionUtils.setField(field, project, ValidationUtils.validateShortField(value, (short) 1000, (short) 9000));
                    break;
                case "photoProjectCreatives":
                    List<String> photoLinks = ValidationUtils.validateStringListField(value, 1000);
                    projectCreativeControllerService.handlePhotoProjectCreativesUpdate(project, photoLinks, errors);
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

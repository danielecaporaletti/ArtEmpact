package com.artempact.backend.mysqlArtempact.controller.profile.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.projectCreative.ProjectCreativeDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.projectCreative.ProjectCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.profile.profileCreative.projectCreative.ProjectCreativeDTOValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/creative/projectCreative")
public class ProjectCreativeController {

    @Autowired
    private ProjectCreativeControllerService projectCreativeControllerService;
    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private ProjectCreativeRepository projectCreativeRepository;
    @Autowired
    private ProjectCreativeDTOValidator projectCreativeDTOValidator;

    @PostMapping
    public ResponseEntity<?> createProjectCreative(JwtAuthenticationToken auth, @RequestBody ProjectCreativeDTO projectCreativeDTO, BindingResult bindingResult) {
        // Controlla i campi obbligatori prima di procedere con la validazione personalizzata
        if (projectCreativeDTO.getName() == null || projectCreativeDTO.getName().isEmpty()) {
            bindingResult.rejectValue("name", "field.required", "Name is required.");
        }
        if (projectCreativeDTO.getYear() == null || projectCreativeDTO.getYear().isEmpty()) {
            bindingResult.rejectValue("year", "field.required", "Year is required.");
        }
        if (projectCreativeDTO.getType() == null || projectCreativeDTO.getType().isEmpty()) {
            bindingResult.rejectValue("type", "field.required", "Type is required.");
        }
        if (projectCreativeDTO.getDescription() == null || projectCreativeDTO.getDescription().isEmpty()) {
            bindingResult.rejectValue("description", "field.required", "Description is required.");
        }

        // Se ci sono errori nei campi obbligatori, ritorna una risposta di errore
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // Crea un DataBinder e applica la validazione personalizzata
        DataBinder binder = new DataBinder(projectCreativeDTO, "projectCreativeDTO");
        binder.setValidator(projectCreativeDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
        }


        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Trova il ProfileCreative associato all'utente loggato
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found"));

        // Crea un nuovo ProjectCreative e lo collega al ProfileCreative
        ProjectCreative newProjectCreative = new ProjectCreative(
                projectCreativeDTO.getName(),
                Short.valueOf(projectCreativeDTO.getYear()),
                projectCreativeDTO.getType(),
                projectCreativeDTO.getDescription()
        );
        newProjectCreative.setProfileCreative(profileCreative);
        // Campi facoltativi
        newProjectCreative.setCustomer(projectCreativeDTO.getCustomer());
        newProjectCreative.setLink(projectCreativeDTO.getLink());

        ProjectCreative savedProjectCreative = projectCreativeRepository.save(newProjectCreative);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProjectCreative);
    }

    @DeleteMapping("/{projectCreativeId}")
    public ResponseEntity<?> deleteProjectCreativeById(JwtAuthenticationToken auth, @PathVariable Long projectCreativeId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        return projectCreativeRepository.findById(projectCreativeId)
                .map(projectCreative -> {
                    // Verifica se l'utente loggato è il proprietario del ProjectCreative
                    if (projectCreative.getProfileCreative().getId().equals(userId)) {
                        projectCreativeRepository.delete(projectCreative);
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this ProjectCreative");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProjectCreative not found"));
    }

    @PatchMapping("/{projectCreativeId}")
    public ResponseEntity<?> updateProjectCreative(JwtAuthenticationToken auth, @PathVariable Long projectCreativeId, @Valid @RequestBody ProjectCreativeDTO projectCreativeDTO, BindingResult bindingResult) {
        ProjectCreative updateProjectCreative = projectCreativeControllerService.updateProjectCreativeWithPatch(auth, projectCreativeId, projectCreativeDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(updateProjectCreative);
    }
}



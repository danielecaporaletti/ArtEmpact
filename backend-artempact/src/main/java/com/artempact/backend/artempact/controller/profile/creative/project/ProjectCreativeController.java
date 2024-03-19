package com.artempact.backend.artempact.controller.profile.creative.project;

import com.artempact.backend.artempact.dto.profile.creative.project.ProjectCreativeDTO;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.entity.profile.creative.project.ProjectCreative;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.project.ProjectCreativeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/profile/creative/projectCreative")
public class ProjectCreativeController {

    private final ProjectCreativeRepository projectCreativeRepository;
    private final ProfileCreativeRepository profileCreativeRepository;

    public ProjectCreativeController(ProjectCreativeRepository projectCreativeRepository, ProfileCreativeRepository profileCreativeRepository) {
        this.projectCreativeRepository = projectCreativeRepository;
        this.profileCreativeRepository = profileCreativeRepository;
    }

    @PostMapping
    public ResponseEntity<ProjectCreative> createProjectCreative(JwtAuthenticationToken auth, @Valid @RequestBody ProjectCreativeDTO projectCreativeDTO) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found"));

        ProjectCreative projectCreative = new ProjectCreative();
        BeanUtils.copyProperties(projectCreativeDTO, projectCreative);
        projectCreative.setProfileCreative(profileCreative);
        ProjectCreative savedProject = projectCreativeRepository.save(projectCreative);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProjectCreative(JwtAuthenticationToken auth, @RequestParam("id") Long projectCreativeId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found"));

        return projectCreativeRepository.findById(projectCreativeId)
                .map(projectCreative -> {
                    if (!projectCreative.getProfileCreative().getId().equals(userId)) {
                        // Se l'utente non è il proprietario, lancia un'eccezione di stato HTTP FORBIDDEN
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: You do not own this ProjectCreative");
                    }
                    // Elimina il ProjectCreative se l'utente è il proprietario
                    projectCreativeRepository.deleteById(projectCreativeId);
                    // Ritorna noContent se l'eliminazione è andata a buon fine
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                // Gestisce il caso in cui il ProjectCreative non viene trovato
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProjectCreative not found"));
    }

    @PatchMapping
    public ResponseEntity<?> updateProjectCreative(JwtAuthenticationToken auth, @RequestParam("id") Long projectCreativeId, @Valid @RequestBody ProjectCreativeDTO projectCreativeDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Verifica l'esistenza dell'utente e se possiede il progetto specificato
        return projectCreativeRepository.findById(projectCreativeId).map(existingProject -> {
                    // Verifica che l'utente sia il proprietario del ProjectCreative
                    if (!existingProject.getProfileCreative().getId().equals(userId)) {
                        // Se non è il proprietario, ritorna un errore
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: You do not own this ProjectCreative");
                    }

                    // Prepara e applica le modifiche
                    String[] nullPropertyNames = getNullPropertyNames(projectCreativeDTO);
                    BeanUtils.copyProperties(projectCreativeDTO, existingProject, nullPropertyNames);

                    // Salva il progetto aggiornato
                    ProjectCreative updatedProject = projectCreativeRepository.save(existingProject);
                    return ResponseEntity.ok(updatedProject);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProjectCreative not found"));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

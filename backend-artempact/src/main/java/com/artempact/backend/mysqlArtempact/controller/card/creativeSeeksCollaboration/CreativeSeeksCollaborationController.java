package com.artempact.backend.mysqlArtempact.controller.card.creativeSeeksCollaboration;

import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocationDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksCollaboration.CreativeSeeksCollaborationDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksCollaboration.junctiontable.CreativeSeeksCollaborationLocationDTO;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocation;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration.junctionTable.CreativeSeeksCollaborationLocation;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.card.creativeSeeksCollaboration.CreativeSeeksCollaborationRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.*;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.card.creativeSeeksCollaboration.CreativeSeeksCollaborationDTOValidator;
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

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/profile/creative/creativeSeeksCollaboration")
public class CreativeSeeksCollaborationController {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private CreativeSeeksCollaborationRepository creativeSeeksCollaborationRepository;
    @Autowired
    private CreativeSeeksCollaborationDTOValidator creativeSeeksCollaborationDTOValidator;
    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private ProfessionalRelationshipRepository professionalRelationshipRepository;
    @Autowired
    private TypeOfCreativeRepository typeOfCreativeRepository;
    @Autowired
    private CreativeSeeksCollaborationControllerService creativeSeeksCollaborationControllerService;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;

    @GetMapping
    public ResponseEntity<?> getCreativeSeeksCollaboration(JwtAuthenticationToken auth) {
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(id)
                .orElse(null);

        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileCreative.getCreativeSeeksCollaborations());
    }

    @PostMapping
    public ResponseEntity<?> createCreativeSeeksCollaboration(JwtAuthenticationToken auth, @RequestBody CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO, BindingResult bindingResult) {
        // Controlla i campi obbligatori prima di procedere con la validazione personalizzata
        if (creativeSeeksCollaborationDTO.getTitle() == null || creativeSeeksCollaborationDTO.getTitle().isEmpty()) {
            bindingResult.rejectValue("title", "field.required.title", "Title is required.");
        }
        if (creativeSeeksCollaborationDTO.getDescription() == null || creativeSeeksCollaborationDTO.getDescription().isEmpty()) {
            bindingResult.rejectValue("description", "field.required.description", "Description is required.");
        }
        if (creativeSeeksCollaborationDTO.getMinProjectBudget() == null || creativeSeeksCollaborationDTO.getMinProjectBudget().isEmpty()) {
            bindingResult.rejectValue("minProjectBudget", "field.required.minProjectBudget", "MinProjectBudget is required.");
        }
        if (creativeSeeksCollaborationDTO.getMaxProjectBudget() == null || creativeSeeksCollaborationDTO.getMaxProjectBudget().isEmpty()) {
            bindingResult.rejectValue("maxProjectBudget", "field.required.maxProjectBudget", "MaxProjectBudget is required.");
        }
        if (creativeSeeksCollaborationDTO.getCardColor() == null || creativeSeeksCollaborationDTO.getCardColor().isEmpty()) {
            bindingResult.rejectValue("cardColor", "field.required.cardColor", "CardColor is required.");
        }
        if (creativeSeeksCollaborationDTO.getEducationalBackground() == null || creativeSeeksCollaborationDTO.getEducationalBackground().isEmpty()) {
            bindingResult.rejectValue("educationalBackground", "field.required.educationalBackground", "EducationalBackground is required.");
        }
        if (creativeSeeksCollaborationDTO.getExperienceLevel() == null || creativeSeeksCollaborationDTO.getExperienceLevel().isEmpty()) {
            bindingResult.rejectValue("experienceLevel", "field.required.experienceLevel", "ExperienceLevel is required.");
        }
        if (creativeSeeksCollaborationDTO.getProfessionalRelationship() == null || creativeSeeksCollaborationDTO.getProfessionalRelationship().isEmpty()) {
            bindingResult.rejectValue("professionalRelationship", "field.required.professionalRelationship", "ProfessionalRelationship is required.");
        }
        if (creativeSeeksCollaborationDTO.getPersonalVisionMission() == null || creativeSeeksCollaborationDTO.getPersonalVisionMission().isEmpty()) {
            bindingResult.rejectValue("personalVisionMission", "field.required.companyVisionMission", "CompanyVisionMission is required.");
        }
        if (creativeSeeksCollaborationDTO.getIdentifyCreativeType() == null || creativeSeeksCollaborationDTO.getIdentifyCreativeType().isEmpty()) {
            bindingResult.rejectValue("identifyCreativeType", "field.required.identifyCreativeType", "IdentifyCreativeType is required.");
        }
        if (creativeSeeksCollaborationDTO.getCreativeSeeksCollaborationLocations() == null) {
            bindingResult.rejectValue("creativeSeeksCollaborationLocations", "field.required.creativeSeeksCollaborationLocations", "CreativeSeeksCollaborationLocations is required.");
        }
        if (creativeSeeksCollaborationDTO.getWorkPreference() == null) {
            bindingResult.rejectValue("workPreference", "field.required.workPreference", "WorkPreference is required.");
        }

        // Se ci sono errori nei campi obbligatori, ritorna una risposta di errore
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // Crea un DataBinder e applica la validazione personalizzata
        DataBinder binder = new DataBinder(creativeSeeksCollaborationDTO, "creativeSeeksCollaborationDTO");
        binder.setValidator(creativeSeeksCollaborationDTOValidator);
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

        // Prepara CreativeSeeksBusinessLocation
        Set<CreativeSeeksCollaborationLocation> creativeSeeksCollaborationLocations = new HashSet<>();
        if (creativeSeeksCollaborationDTO.getCreativeSeeksCollaborationLocations() != null) {
            for (CreativeSeeksCollaborationLocationDTO locationDTO : creativeSeeksCollaborationDTO.getCreativeSeeksCollaborationLocations()) {
                CreativeSeeksCollaborationLocation newLocation = convertToEntity(locationDTO);
                creativeSeeksCollaborationLocations.add(newLocation);
            }
        }

        // Crea un nuovo CreativeSeeksCollaboration e lo collega al ProfileCreative
        CreativeSeeksCollaboration newCreativeSeeksCollaboration = new CreativeSeeksCollaboration(
                creativeSeeksCollaborationDTO.getTitle(),
                creativeSeeksCollaborationDTO.getDescription(),
                Integer.valueOf(creativeSeeksCollaborationDTO.getMinProjectBudget()),
                Integer.valueOf(creativeSeeksCollaborationDTO.getMaxProjectBudget()),
                creativeSeeksCollaborationDTO.getCardColor(),
                educationTypeRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getEducationalBackground()))
                        .orElseThrow(() -> new EntityNotFoundException("EducationType not found")),
                experienceLevelRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getExperienceLevel()))
                        .orElseThrow(() -> new EntityNotFoundException("ExperienceLevel not found")),
                professionalRelationshipRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getProfessionalRelationship()))
                        .orElseThrow(() -> new EntityNotFoundException("ProfessionalRelationship not found")),
                creativeSeeksCollaborationDTO.getPersonalVisionMission(),
                typeOfCreativeRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getIdentifyCreativeType()))
                        .orElseThrow(() -> new EntityNotFoundException("TypeOfCreative not found")),
                creativeSeeksCollaborationLocations,
                workPreferenceRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getWorkPreference()))
                        .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"))
        );

        newCreativeSeeksCollaboration.setProfileCreative(profileCreative);

        CreativeSeeksCollaboration savedCreativeSeeksCollaboration = creativeSeeksCollaborationRepository.save(newCreativeSeeksCollaboration);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCreativeSeeksCollaboration);
    }

    @PatchMapping("/{creativeSeeksCollaborationId}")
    public ResponseEntity<?> updateCreativeSeeksCollaboration(JwtAuthenticationToken auth, @PathVariable Long creativeSeeksCollaborationId, @Valid @RequestBody CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO, BindingResult bindingResult) {
        CreativeSeeksCollaboration updateCreativeSeeksCollaboration = creativeSeeksCollaborationControllerService.updateCreativeSeeksCollaborationWithPatch(auth, creativeSeeksCollaborationId, creativeSeeksCollaborationDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(updateCreativeSeeksCollaboration);
    }

    @DeleteMapping("/{creativeSeeksCollaborationId}")
    public ResponseEntity<?> deleteCreativeSeeksCollaboration(JwtAuthenticationToken auth, @PathVariable Long creativeSeeksCollaborationId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElse(null);

        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova l'entità CreativeSeeksCollaboration
        return creativeSeeksCollaborationRepository.findById(creativeSeeksCollaborationId)
                .map(creativeSeeksCollaboration -> {
                    // Verifica che l'entità appartenga al profilo creativo dell'utente autenticato
                    if (creativeSeeksCollaboration.getProfileCreative().getId().equals(userId)) {
                        // Elimina l'entità
                        creativeSeeksCollaborationRepository.delete(creativeSeeksCollaboration);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario dell'entità
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this creativeSeeksCollaboration");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("CreativeSeeksCollaboration not found"));
    }

    private CreativeSeeksCollaborationLocation convertToEntity(CreativeSeeksCollaborationLocationDTO dto) {
        CreativeSeeksCollaborationLocation entity = new CreativeSeeksCollaborationLocation();
        entity.setCity(dto.getCity());
        entity.setProvince(dto.getProvince());
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());
        return entity;
    }

}

package com.artempact.backend.mysqlArtempact.controller.card.creativeSeeksBusiness;

import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.CreativeSeeksBusinessDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocationDTO;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocation;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.card.creativeSeeksBusiness.CreativeSeeksBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.*;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.card.creativeSeeksBusiness.CreativeSeeksBusinessDTOValidator;
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
@RequestMapping("/profile/creative/creativeSeeksBusiness")
public class CreativeSeeksBusinessController {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private CreativeSeeksBusinessRepository creativeSeeksBusinessRepository;
    @Autowired
    private CreativeSeeksBusinessDTOValidator creativeSeeksBusinessDTOValidator;
    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private ProfessionalRelationshipRepository professionalRelationshipRepository;
    @Autowired
    private TypeOfBusinessRepository typeOfBusinessRepository;
    @Autowired
    private CreativeSeeksBusinessControllerService creativeSeeksBusinessControllerService;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;

    @GetMapping
    public ResponseEntity<?> getCreativeSeeksBusiness(JwtAuthenticationToken auth) {
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(id)
                .orElse(null);

        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileCreative.getCreativeSeeksBusinesses());
    }

    @PostMapping
    public ResponseEntity<?> createCreativeSeeksBusiness(JwtAuthenticationToken auth, @RequestBody CreativeSeeksBusinessDTO creativeSeeksBusinessDTO, BindingResult bindingResult) {
        // Controlla i campi obbligatori prima di procedere con la validazione personalizzata
        if (creativeSeeksBusinessDTO.getTitle() == null || creativeSeeksBusinessDTO.getTitle().isEmpty()) {
            bindingResult.rejectValue("title", "field.required.title", "Title is required.");
        }
        if (creativeSeeksBusinessDTO.getDescription() == null || creativeSeeksBusinessDTO.getDescription().isEmpty()) {
            bindingResult.rejectValue("description", "field.required.description", "Description is required.");
        }
        if (creativeSeeksBusinessDTO.getMinProjectBudget() == null || creativeSeeksBusinessDTO.getMinProjectBudget().isEmpty()) {
            bindingResult.rejectValue("minProjectBudget", "field.required.minProjectBudget", "MinProjectBudget is required.");
        }
        if (creativeSeeksBusinessDTO.getMaxProjectBudget() == null || creativeSeeksBusinessDTO.getMaxProjectBudget().isEmpty()) {
            bindingResult.rejectValue("maxProjectBudget", "field.required.maxProjectBudget", "MaxProjectBudget is required.");
        }
        if (creativeSeeksBusinessDTO.getCardColor() == null || creativeSeeksBusinessDTO.getCardColor().isEmpty()) {
            bindingResult.rejectValue("cardColor", "field.required.cardColor", "CardColor is required.");
        }
        if (creativeSeeksBusinessDTO.getEducationalBackground() == null || creativeSeeksBusinessDTO.getEducationalBackground().isEmpty()) {
            bindingResult.rejectValue("educationalBackground", "field.required.educationalBackground", "EducationalBackground is required.");
        }
        if (creativeSeeksBusinessDTO.getExperienceLevel() == null || creativeSeeksBusinessDTO.getExperienceLevel().isEmpty()) {
            bindingResult.rejectValue("experienceLevel", "field.required.experienceLevel", "ExperienceLevel is required.");
        }
        if (creativeSeeksBusinessDTO.getProfessionalRelationship() == null || creativeSeeksBusinessDTO.getProfessionalRelationship().isEmpty()) {
            bindingResult.rejectValue("professionalRelationship", "field.required.professionalRelationship", "ProfessionalRelationship is required.");
        }
        if (creativeSeeksBusinessDTO.getPositionDescription() == null || creativeSeeksBusinessDTO.getPositionDescription().isEmpty()) {
            bindingResult.rejectValue("positionDescription", "field.required.positionDescription", "PositionDescription is required.");
        }
        if (creativeSeeksBusinessDTO.getIdentifyBusinessType() == null || creativeSeeksBusinessDTO.getIdentifyBusinessType().isEmpty()) {
            bindingResult.rejectValue("identifyBusinessType", "field.required.identifyBusinessType", "IdentifyBusinesType is required.");
        }
        if (creativeSeeksBusinessDTO.getCreativeSeeksBusinessLocations() == null) {
            bindingResult.rejectValue("creativeSeeksBusinessLocations", "field.required.creativeSeeksBusinessLocations", "CreativeSeeksBusinessLocations is required.");
        }
        if (creativeSeeksBusinessDTO.getWorkPreference() == null) {
            bindingResult.rejectValue("workPreference", "field.required.workPreference", "WorkPreference is required.");
        }

        // Se ci sono errori nei campi obbligatori, ritorna una risposta di errore
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // Crea un DataBinder e applica la validazione personalizzata
        DataBinder binder = new DataBinder(creativeSeeksBusinessDTO, "creativeSeeksBusinessDTO");
        binder.setValidator(creativeSeeksBusinessDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
        }

        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Trova il ProfileBusiness associato all'utente loggato
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found"));

        // Prepara CreativeSeeksBusinessLocation
        Set<CreativeSeeksBusinessLocation> creativeSeeksBusinessLocations = new HashSet<>();
        if (creativeSeeksBusinessDTO.getCreativeSeeksBusinessLocations() != null) {
            for (CreativeSeeksBusinessLocationDTO locationDTO : creativeSeeksBusinessDTO.getCreativeSeeksBusinessLocations()) {
                CreativeSeeksBusinessLocation newLocation = convertToEntity(locationDTO);
                creativeSeeksBusinessLocations.add(newLocation);
            }
        }

        // Crea un nuovo CreativeSeeksBusiness e lo collega al ProfileCreative
        CreativeSeeksBusiness newCreativeSeeksBusiness = new CreativeSeeksBusiness(
                creativeSeeksBusinessDTO.getTitle(),
                creativeSeeksBusinessDTO.getDescription(),
                Integer.valueOf(creativeSeeksBusinessDTO.getMinProjectBudget()),
                Integer.valueOf(creativeSeeksBusinessDTO.getMaxProjectBudget()),
                creativeSeeksBusinessDTO.getCardColor(),
                educationTypeRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getEducationalBackground()))
                        .orElseThrow(() -> new EntityNotFoundException("EducationType not found")),
                experienceLevelRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getExperienceLevel()))
                        .orElseThrow(() -> new EntityNotFoundException("ExperienceLevel not found")),
                professionalRelationshipRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getProfessionalRelationship()))
                        .orElseThrow(() -> new EntityNotFoundException("ProfessionalRelationship not found")),
                creativeSeeksBusinessDTO.getPositionDescription(),
                typeOfBusinessRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getIdentifyBusinessType()))
                        .orElseThrow(() -> new EntityNotFoundException("TypeOfBusiness not found")),
                creativeSeeksBusinessLocations,
                workPreferenceRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getWorkPreference()))
                        .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"))
        );

        newCreativeSeeksBusiness.setProfileCreative(profileCreative);

        CreativeSeeksBusiness savedCreativeSeeksBusiness = creativeSeeksBusinessRepository.save(newCreativeSeeksBusiness);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCreativeSeeksBusiness);
    }

    @PatchMapping("/{creativeSeeksBusinessId}")
    public ResponseEntity<?> updateCreativeSeeksBusiness(JwtAuthenticationToken auth, @PathVariable Long creativeSeeksBusinessId, @Valid @RequestBody CreativeSeeksBusinessDTO creativeSeeksBusinessDTO, BindingResult bindingResult) {
        CreativeSeeksBusiness updateCreativeSeeksBusiness = creativeSeeksBusinessControllerService.updateCreativeSeeksBusinessWithPatch(auth, creativeSeeksBusinessId, creativeSeeksBusinessDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(updateCreativeSeeksBusiness);
    }

    @DeleteMapping("/{creativeSeeksBusinessId}")
    public ResponseEntity<?> deleteCreativeSeeksBusiness(JwtAuthenticationToken auth, @PathVariable Long creativeSeeksBusinessId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElse(null);

        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova l'entità CreativeSeeksBusiness
        return creativeSeeksBusinessRepository.findById(creativeSeeksBusinessId)
                .map(creativeSeeksBusiness -> {
                    // Verifica che l'entità appartenga al profilo creativo dell'utente autenticato
                    if (creativeSeeksBusiness.getProfileCreative().getId().equals(userId)) {
                        // Elimina l'entità
                        creativeSeeksBusinessRepository.delete(creativeSeeksBusiness);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario dell'entità
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this creativeSeeksBusiness");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("CreativeSeeksBusiness not found"));
    }

    private CreativeSeeksBusinessLocation convertToEntity(CreativeSeeksBusinessLocationDTO dto) {
        CreativeSeeksBusinessLocation entity = new CreativeSeeksBusinessLocation();
        entity.setCity(dto.getCity());
        entity.setProvince(dto.getProvince());
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());
        return entity;
    }
}

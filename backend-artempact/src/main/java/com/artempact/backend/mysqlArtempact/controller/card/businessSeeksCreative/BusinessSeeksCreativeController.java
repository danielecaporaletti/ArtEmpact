package com.artempact.backend.mysqlArtempact.controller.card.businessSeeksCreative;

import com.artempact.backend.mysqlArtempact.dto.card.businessSeeksCreative.BusinessSeeksCreativeDTO;
import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.repository.card.businessSeeksCreative.BusinessSeeksCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.*;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.ProfileBusinessRepository;
import com.artempact.backend.mysqlArtempact.validator.card.BusinessSeeksCreative.BusinessSeeksCreativeDTOValidator;
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
@RequestMapping("/profile/business/businessSeeksCreative")
public class BusinessSeeksCreativeController {

    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;
    @Autowired
    private BusinessSeeksCreativeRepository businessSeeksCreativeRepository;
    @Autowired
    private BusinessSeeksCreativeDTOValidator businessSeeksCreativeDTOValidator;
    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private ProfessionalRelationshipRepository professionalRelationshipRepository;
    @Autowired
    private TypeOfCreativeRepository typeOfCreativeRepository;
    @Autowired
    private BusinessSeeksCreativeControllerService businessSeeksCreativeControllerService;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;

    @GetMapping
    public ResponseEntity<?> getBusinessSeeksCreative(JwtAuthenticationToken auth) {
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileBusiness profileBusiness = profileBusinessRepository.findById(id)
                .orElse(null);

        if (profileBusiness == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileBusiness.getBusinessSeeksCreatives());
    }

    @PostMapping
    public ResponseEntity<?> createBusinessSeeksCreative(JwtAuthenticationToken auth, @RequestBody BusinessSeeksCreativeDTO businessSeeksCreativeDTO, BindingResult bindingResult) {
        // Controlla i campi obbligatori prima di procedere con la validazione personalizzata
        if (businessSeeksCreativeDTO.getTitle() == null || businessSeeksCreativeDTO.getTitle().isEmpty()) {
            bindingResult.rejectValue("title", "field.required.title", "Title is required.");
        }
        if (businessSeeksCreativeDTO.getDescription() == null || businessSeeksCreativeDTO.getDescription().isEmpty()) {
            bindingResult.rejectValue("description", "field.required.description", "Description is required.");
        }
        if (businessSeeksCreativeDTO.getMinProjectBudget() == null || businessSeeksCreativeDTO.getMinProjectBudget().isEmpty()) {
            bindingResult.rejectValue("minProjectBudget", "field.required.minProjectBudget", "MinProjectBudget is required.");
        }
        if (businessSeeksCreativeDTO.getMaxProjectBudget() == null || businessSeeksCreativeDTO.getMaxProjectBudget().isEmpty()) {
            bindingResult.rejectValue("maxProjectBudget", "field.required.maxProjectBudget", "MaxProjectBudget is required.");
        }
        if (businessSeeksCreativeDTO.getCardColor() == null || businessSeeksCreativeDTO.getCardColor().isEmpty()) {
            bindingResult.rejectValue("cardColor", "field.required.cardColor", "CardColor is required.");
        }
        if (businessSeeksCreativeDTO.getEducationalBackground() == null || businessSeeksCreativeDTO.getEducationalBackground().isEmpty()) {
            bindingResult.rejectValue("educationalBackground", "field.required.educationalBackground", "EducationalBackground is required.");
        }
        if (businessSeeksCreativeDTO.getExperienceLevel() == null || businessSeeksCreativeDTO.getExperienceLevel().isEmpty()) {
            bindingResult.rejectValue("experienceLevel", "field.required.experienceLevel", "ExperienceLevel is required.");
        }
        if (businessSeeksCreativeDTO.getProfessionalRelationship() == null || businessSeeksCreativeDTO.getProfessionalRelationship().isEmpty()) {
            bindingResult.rejectValue("professionalRelationship", "field.required.professionalRelationship", "ProfessionalRelationship is required.");
        }
        if (businessSeeksCreativeDTO.getCompanyVisionMission() == null || businessSeeksCreativeDTO.getCompanyVisionMission().isEmpty()) {
            bindingResult.rejectValue("companyVisionMission", "field.required.companyVisionMission", "CompanyVisionMission is required.");
        }
        if (businessSeeksCreativeDTO.getIdentifyCreativeType() == null || businessSeeksCreativeDTO.getIdentifyCreativeType().isEmpty()) {
            bindingResult.rejectValue("identifyCreativeType", "field.required.identifyCreativeType", "IdentifyCreativeType is required.");
        }
        if (businessSeeksCreativeDTO.getWorkPreference() == null) {
            bindingResult.rejectValue("workPreference", "field.required.workPreference", "WorkPreference is required.");
        }

        // Se ci sono errori nei campi obbligatori, ritorna una risposta di errore
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // Crea un DataBinder e applica la validazione personalizzata
        DataBinder binder = new DataBinder(businessSeeksCreativeDTO, "businessSeeksCreativeDTO");
        binder.setValidator(businessSeeksCreativeDTOValidator);
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
        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileBusiness not found"));

        // Crea un nuovo BusinessSeeksCreative e lo collega al ProfileBusiness
        BusinessSeeksCreative newBusinessSeeksCreative = new BusinessSeeksCreative(
                businessSeeksCreativeDTO.getTitle(),
                businessSeeksCreativeDTO.getDescription(),
                Integer.valueOf(businessSeeksCreativeDTO.getMinProjectBudget()),
                Integer.valueOf(businessSeeksCreativeDTO.getMaxProjectBudget()),
                businessSeeksCreativeDTO.getCardColor(),
                educationTypeRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getEducationalBackground()))
                        .orElseThrow(() -> new EntityNotFoundException("EducationType not found")),
                experienceLevelRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getExperienceLevel()))
                        .orElseThrow(() -> new EntityNotFoundException("ExperienceLevel not found")),
                professionalRelationshipRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getProfessionalRelationship()))
                        .orElseThrow(() -> new EntityNotFoundException("ProfessionalRelationship not found")),
                businessSeeksCreativeDTO.getCompanyVisionMission(),
                typeOfCreativeRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getIdentifyCreativeType()))
                        .orElseThrow(() -> new EntityNotFoundException("TypeOfCreative not found")),
                workPreferenceRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getWorkPreference()))
                        .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"))
        );
        newBusinessSeeksCreative.setProfileBusiness(profileBusiness);

        BusinessSeeksCreative savedBusinessSeeksCreative = businessSeeksCreativeRepository.save(newBusinessSeeksCreative);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBusinessSeeksCreative);
    }

    @PatchMapping("/{businessSeeksCreativeId}")
    public ResponseEntity<?> updateBusinessSeeksCreative(JwtAuthenticationToken auth, @PathVariable Long businessSeeksCreativeId, @Valid @RequestBody BusinessSeeksCreativeDTO businessSeeksCreativeDTO, BindingResult bindingResult) {
        BusinessSeeksCreative updateBusinessSeeksCreative = businessSeeksCreativeControllerService.updateBusinessSeeksCreativeWithPatch(auth, businessSeeksCreativeId, businessSeeksCreativeDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(updateBusinessSeeksCreative);
    }

    @DeleteMapping("/{businessSeeksCreativeId}")
    public ResponseEntity<?> deleteBusinessSeeksCreative(JwtAuthenticationToken auth, @PathVariable Long businessSeeksCreativeId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElse(null);

        if (profileBusiness == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova l'entità BusinessSeeksCreative
        return businessSeeksCreativeRepository.findById(businessSeeksCreativeId)
                .map(businessSeeksCreative -> {
                    // Verifica che l'entità appartenga al profilo business dell'utente autenticato
                    if (businessSeeksCreative.getProfileBusiness().getId().equals(userId)) {
                        // Elimina l'entità
                        businessSeeksCreativeRepository.delete(businessSeeksCreative);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario dell'entità
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this businessSeeksCreative");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("BusinessSeeksCreative not found"));
    }


}

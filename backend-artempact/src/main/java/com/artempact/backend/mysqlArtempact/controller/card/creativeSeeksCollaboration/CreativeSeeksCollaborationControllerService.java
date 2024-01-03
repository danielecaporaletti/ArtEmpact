package com.artempact.backend.mysqlArtempact.controller.card.creativeSeeksCollaboration;

import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksCollaboration.CreativeSeeksCollaborationDTO;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.card.creativeSeeksCollaboration.CreativeSeeksCollaborationRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.ExperienceLevelRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.ProfessionalRelationshipRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.card.creativeSeeksCollaboration.CreativeSeeksCollaborationDTOValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.Optional;

@Service
public class CreativeSeeksCollaborationControllerService {

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

    @Transactional
    public CreativeSeeksCollaboration updateCreativeSeeksCollaborationWithPatch(JwtAuthenticationToken auth, Long creativeSeeksCollaborationId, CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO, BindingResult bindingResult) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        // controlliamo se l'utente esiste
        ProfileCreative existingProfileCreative = profileCreativeRepository.findById(id).orElse(null);
        if (existingProfileCreative == null) {
            bindingResult.reject("notfound", "User not found.");
            return null;
        }

        // controlliamo se l'utente ha giá questa card
        Optional<CreativeSeeksCollaboration> existingCreativeSeeksCollaborationOpt = creativeSeeksCollaborationRepository.findById(creativeSeeksCollaborationId);
        if (!existingCreativeSeeksCollaborationOpt.isPresent()) {
            bindingResult.reject("notfound", "CreativeSeeksCollaboration not found.");
            return null;
        }

        // Estraendo il valore da Optional
        CreativeSeeksCollaboration existingCreativeSeeksCollaboration = existingCreativeSeeksCollaborationOpt.get();

        // Collega il ProfileBusiness al BusinessSeeksCreative
        existingCreativeSeeksCollaboration.setProfileCreative(existingProfileCreative);

        // Crea un DataBinder e imposta il nome dell'oggetto
        DataBinder binder = new DataBinder(creativeSeeksCollaborationDTO, "creativeSeeksCollaborationDTO");
        binder.setValidator(creativeSeeksCollaborationDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            return null;
        }

        if (creativeSeeksCollaborationDTO.getTitle() != null) {
            existingCreativeSeeksCollaboration.setTitle(creativeSeeksCollaborationDTO.getTitle().trim());
        }
        if (creativeSeeksCollaborationDTO.getDescription() != null) {
            existingCreativeSeeksCollaboration.setDescription(creativeSeeksCollaborationDTO.getDescription().trim());
        }
        if (creativeSeeksCollaborationDTO.getMinProjectBudget() != null) {
            existingCreativeSeeksCollaboration.setMinProjectBudget(Integer.valueOf(creativeSeeksCollaborationDTO.getMinProjectBudget().trim()));
        }
        if (creativeSeeksCollaborationDTO.getMaxProjectBudget() != null) {
            existingCreativeSeeksCollaboration.setMaxProjectBudget(Integer.valueOf(creativeSeeksCollaborationDTO.getMaxProjectBudget().trim()));
        }
        if (creativeSeeksCollaborationDTO.getCardColor() != null) {
            existingCreativeSeeksCollaboration.setCardColor(creativeSeeksCollaborationDTO.getCardColor().trim());
        }
        if (creativeSeeksCollaborationDTO.getEducationalBackground() != null) {
            EducationType educationType = educationTypeRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getEducationalBackground()))
                    .orElseThrow(() -> new EntityNotFoundException("EducationType not found"));
            existingCreativeSeeksCollaboration.setEducationalBackground(educationType);
        }
        if (creativeSeeksCollaborationDTO.getExperienceLevel() != null) {
            ExperienceLevel experienceLevel = experienceLevelRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getExperienceLevel()))
                    .orElseThrow(() -> new EntityNotFoundException("ExperienceLevel not found"));
            existingCreativeSeeksCollaboration.setExperienceLevel(experienceLevel);
        }
        if (creativeSeeksCollaborationDTO.getProfessionalRelationship() != null) {
            ProfessionalRelationship professionalRelationship = professionalRelationshipRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getProfessionalRelationship()))
                    .orElseThrow(() -> new EntityNotFoundException("ProfessionalRelationship not found"));
            existingCreativeSeeksCollaboration.setProfessionalRelationship(professionalRelationship);
        }
        if (creativeSeeksCollaborationDTO.getPersonalVisionMission() != null) {
            existingCreativeSeeksCollaboration.setPersonalVisionMission(creativeSeeksCollaborationDTO.getPersonalVisionMission().trim());
        }
        if (creativeSeeksCollaborationDTO.getIdentifyCreativeType() != null) {
            TypeOfCreative typeOfCreative = typeOfCreativeRepository.findById(Short.valueOf(creativeSeeksCollaborationDTO.getIdentifyCreativeType()))
                    .orElseThrow(() -> new EntityNotFoundException("typeOfCreative not found"));
            existingCreativeSeeksCollaboration.setIdentifyCreativeType(typeOfCreative);
        }
        if (creativeSeeksCollaborationDTO.getLocality() != null) {
            existingCreativeSeeksCollaboration.getLocality().setCity(creativeSeeksCollaborationDTO.getLocality().getCity().trim());
            existingCreativeSeeksCollaboration.getLocality().setProvince(creativeSeeksCollaborationDTO.getLocality().getProvince().trim());
            existingCreativeSeeksCollaboration.getLocality().setLat(creativeSeeksCollaborationDTO.getLocality().getLat());
            existingCreativeSeeksCollaboration.getLocality().setLon(creativeSeeksCollaborationDTO.getLocality().getLon());
        }

        return creativeSeeksCollaborationRepository.save(existingCreativeSeeksCollaboration);
    }
}

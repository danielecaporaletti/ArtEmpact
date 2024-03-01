package com.artempact.backend.mysqlArtempact.controller.card.businessSeeksCreative;

import com.artempact.backend.mysqlArtempact.dto.card.businessSeeksCreative.BusinessSeeksCreativeDTO;
import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.*;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.repository.card.businessSeeksCreative.BusinessSeeksCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.*;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.ProfileBusinessRepository;
import com.artempact.backend.mysqlArtempact.validator.card.BusinessSeeksCreative.BusinessSeeksCreativeDTOValidator;
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
public class BusinessSeeksCreativeControllerService {

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
    private WorkPreferenceRepository workPreferenceRepository;

    @Transactional
    public BusinessSeeksCreative updateBusinessSeeksCreativeWithPatch(JwtAuthenticationToken auth, Long businessSeeksCreativeId, BusinessSeeksCreativeDTO businessSeeksCreativeDTO, BindingResult bindingResult) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        // controlliamo se l'utente esiste
        ProfileBusiness existingProfileBusiness = profileBusinessRepository.findById(id).orElse(null);
        if (existingProfileBusiness == null) {
            bindingResult.reject("notfound", "User not found.");
            return null;
        }

        // controlliamo se l'utente ha giá questa card
        Optional<BusinessSeeksCreative> existingBusinessSeeksCreativeOpt = businessSeeksCreativeRepository.findById(businessSeeksCreativeId);
        if (!existingBusinessSeeksCreativeOpt.isPresent()) {
            bindingResult.reject("notfound", "BusinessSeeksCreative not found.");
            return null;
        }

        // Estraendo il valore da Optional
        BusinessSeeksCreative existingBusinessSeeksCreative = existingBusinessSeeksCreativeOpt.get();

        // Collega il ProfileBusiness al BusinessSeeksCreative
        existingBusinessSeeksCreative.setProfileBusiness(existingProfileBusiness);

        // Crea un DataBinder e imposta il nome dell'oggetto
        DataBinder binder = new DataBinder(businessSeeksCreativeDTO, "businessSeeksCreativeDTO");
        binder.setValidator(businessSeeksCreativeDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            return null;
        }

        if (businessSeeksCreativeDTO.getTitle() != null) {
            existingBusinessSeeksCreative.setTitle(businessSeeksCreativeDTO.getTitle().trim());
        }
        if (businessSeeksCreativeDTO.getDescription() != null) {
            existingBusinessSeeksCreative.setDescription(businessSeeksCreativeDTO.getDescription().trim());
        }
        if (businessSeeksCreativeDTO.getMinProjectBudget() != null) {
            existingBusinessSeeksCreative.setMinProjectBudget(Integer.valueOf(businessSeeksCreativeDTO.getMinProjectBudget().trim()));
        }
        if (businessSeeksCreativeDTO.getMaxProjectBudget() != null) {
            existingBusinessSeeksCreative.setMaxProjectBudget(Integer.valueOf(businessSeeksCreativeDTO.getMaxProjectBudget().trim()));
        }
        if (businessSeeksCreativeDTO.getCardColor() != null) {
            existingBusinessSeeksCreative.setCardColor(businessSeeksCreativeDTO.getCardColor().trim());
        }
        if (businessSeeksCreativeDTO.getEducationalBackground() != null) {
            EducationType educationType = educationTypeRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getEducationalBackground()))
                    .orElseThrow(() -> new EntityNotFoundException("EducationType not found"));
            existingBusinessSeeksCreative.setEducationalBackground(educationType);
        }
        if (businessSeeksCreativeDTO.getExperienceLevel() != null) {
            ExperienceLevel experienceLevel = experienceLevelRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getExperienceLevel()))
                    .orElseThrow(() -> new EntityNotFoundException("ExperienceLevel not found"));
            existingBusinessSeeksCreative.setExperienceLevel(experienceLevel);
        }
        if (businessSeeksCreativeDTO.getProfessionalRelationship() != null) {
            ProfessionalRelationship professionalRelationship = professionalRelationshipRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getProfessionalRelationship()))
                    .orElseThrow(() -> new EntityNotFoundException("ProfessionalRelationship not found"));
            existingBusinessSeeksCreative.setProfessionalRelationship(professionalRelationship);
        }
        if (businessSeeksCreativeDTO.getCompanyVisionMission() != null) {
            existingBusinessSeeksCreative.setCompanyVisionMission(businessSeeksCreativeDTO.getCompanyVisionMission().trim());
        }
        if (businessSeeksCreativeDTO.getIdentifyCreativeType() != null) {
            TypeOfCreative typeOfCreative = typeOfCreativeRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getIdentifyCreativeType()))
                    .orElseThrow(() -> new EntityNotFoundException("TypeOfBusiness not found"));
            existingBusinessSeeksCreative.setIdentifyCreativeType(typeOfCreative);
        }
        if (businessSeeksCreativeDTO.getWorkPreference() != null) {
            WorkPreference workPreference = workPreferenceRepository.findById(Short.valueOf(businessSeeksCreativeDTO.getWorkPreference()))
                    .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"));
            existingBusinessSeeksCreative.setWorkPreference(workPreference);
        }

        return businessSeeksCreativeRepository.save(existingBusinessSeeksCreative);
    }
}

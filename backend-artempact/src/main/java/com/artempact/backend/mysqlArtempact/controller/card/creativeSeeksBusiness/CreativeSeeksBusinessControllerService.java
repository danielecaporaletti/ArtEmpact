package com.artempact.backend.mysqlArtempact.controller.card.creativeSeeksBusiness;

import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.CreativeSeeksBusinessDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.junctionTable.JobSearchLocationCardDTO;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable.JobSearchLocationCard;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.card.creativeSeeksBusiness.CreativeSeeksBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.ExperienceLevelRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.ProfessionalRelationshipRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.card.creativeSeeksBusiness.CreativeSeeksBusinessDTOValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.Optional;
import java.util.Set;

@Service
public class CreativeSeeksBusinessControllerService {


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

    @Transactional
    public CreativeSeeksBusiness updateCreativeSeeksBusinessWithPatch(JwtAuthenticationToken auth, Long creativeSeeksBusinessId, CreativeSeeksBusinessDTO creativeSeeksBusinessDTO, BindingResult bindingResult) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        // controlliamo se l'utente esiste
        ProfileCreative existingProfileCreative = profileCreativeRepository.findById(id).orElse(null);
        if (existingProfileCreative == null) {
            bindingResult.reject("notfound", "User not found.");
            return null;
        }

        // controlliamo se l'utente ha giá questa card
        Optional<CreativeSeeksBusiness> existingCreativeSeeksBusinessOpt = creativeSeeksBusinessRepository.findById(creativeSeeksBusinessId);
        if (!existingCreativeSeeksBusinessOpt.isPresent()) {
            bindingResult.reject("notfound", "CreativeSeeksBusiness not found.");
            return null;
        }

        // Estraendo il valore da Optional
        CreativeSeeksBusiness existingCreativeSeeksBusiness = existingCreativeSeeksBusinessOpt.get();

        // Collega il ProfileBusiness al BusinessSeeksCreative
        existingCreativeSeeksBusiness.setProfileCreative(existingProfileCreative);

        // Crea un DataBinder e imposta il nome dell'oggetto
        DataBinder binder = new DataBinder(creativeSeeksBusinessDTO, "creativeSeeksBusinessDTO");
        binder.setValidator(creativeSeeksBusinessDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            return null;
        }

        /*


        Set<jobSearchLocationCards>
         */

        if (creativeSeeksBusinessDTO.getTitle() != null) {
            existingCreativeSeeksBusiness.setTitle(creativeSeeksBusinessDTO.getTitle().trim());
        }
        if (creativeSeeksBusinessDTO.getDescription() != null) {
            existingCreativeSeeksBusiness.setDescription(creativeSeeksBusinessDTO.getDescription().trim());
        }
        if (creativeSeeksBusinessDTO.getMinProjectBudget() != null) {
            existingCreativeSeeksBusiness.setMinProjectBudget(Integer.valueOf(creativeSeeksBusinessDTO.getMinProjectBudget().trim()));
        }
        if (creativeSeeksBusinessDTO.getMaxProjectBudget() != null) {
            existingCreativeSeeksBusiness.setMaxProjectBudget(Integer.valueOf(creativeSeeksBusinessDTO.getMaxProjectBudget().trim()));
        }
        if (creativeSeeksBusinessDTO.getCardColor() != null) {
            existingCreativeSeeksBusiness.setCardColor(creativeSeeksBusinessDTO.getCardColor().trim());
        }
        if (creativeSeeksBusinessDTO.getEducationalBackground() != null) {
            EducationType educationType = educationTypeRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getEducationalBackground()))
                    .orElseThrow(() -> new EntityNotFoundException("EducationType not found"));
            existingCreativeSeeksBusiness.setEducationalBackground(educationType);
        }
        if (creativeSeeksBusinessDTO.getExperienceLevel() != null) {
            ExperienceLevel experienceLevel = experienceLevelRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getExperienceLevel()))
                    .orElseThrow(() -> new EntityNotFoundException("ExperienceLevel not found"));
            existingCreativeSeeksBusiness.setExperienceLevel(experienceLevel);
        }
        if (creativeSeeksBusinessDTO.getProfessionalRelationship() != null) {
            ProfessionalRelationship professionalRelationship = professionalRelationshipRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getProfessionalRelationship()))
                    .orElseThrow(() -> new EntityNotFoundException("ProfessionalRelationship not found"));
            existingCreativeSeeksBusiness.setProfessionalRelationship(professionalRelationship);
        }
        if (creativeSeeksBusinessDTO.getPositionDescription() != null) {
            existingCreativeSeeksBusiness.setPositionDescription(creativeSeeksBusinessDTO.getPositionDescription().trim());
        }
        if (creativeSeeksBusinessDTO.getIdentifyBusinesType() != null) {
            TypeOfBusiness typeOfBusiness = typeOfBusinessRepository.findById(Short.valueOf(creativeSeeksBusinessDTO.getIdentifyBusinesType()))
                    .orElseThrow(() -> new EntityNotFoundException("TypeOfBusiness not found"));
            existingCreativeSeeksBusiness.setIdentifyBusinesType(typeOfBusiness);
        }
        if (creativeSeeksBusinessDTO.getJobSearchLocationCards() != null) {
            Set<JobSearchLocationCard> currentLocations = existingCreativeSeeksBusiness.getJobSearchLocationCards();

            for (JobSearchLocationCardDTO locationDTO : creativeSeeksBusinessDTO.getJobSearchLocationCards()) {
                // Controlla se la location esiste già
                boolean locationExists = currentLocations.stream()
                        .anyMatch(loc -> loc.getCity().equals(locationDTO.getCity()) && loc.getProvince().equals(locationDTO.getProvince()));

                // Se non esiste, aggiungila
                if (!locationExists) {
                    JobSearchLocationCard newLocation = convertToEntity(locationDTO, existingCreativeSeeksBusiness);
                    currentLocations.add(newLocation);
                }
            }
        }
        return creativeSeeksBusinessRepository.save(existingCreativeSeeksBusiness);
    }

    private JobSearchLocationCard convertToEntity(JobSearchLocationCardDTO dto, CreativeSeeksBusiness creativeSeeksBusiness) {
        JobSearchLocationCard entity = new JobSearchLocationCard();
        entity.setCity(dto.getCity());
        entity.setProvince(dto.getProvince());
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());
        entity.setCreativeSeeksBusiness(creativeSeeksBusiness);
        return entity;
    }
}

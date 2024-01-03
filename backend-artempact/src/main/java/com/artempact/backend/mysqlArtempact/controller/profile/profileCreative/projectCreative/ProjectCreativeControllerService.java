package com.artempact.backend.mysqlArtempact.controller.profile.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.projectCreative.ProjectCreativeDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.projectCreative.ProjectCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.profile.profileCreative.projectCreative.ProjectCreativeDTOValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.Optional;

@Service
public class ProjectCreativeControllerService {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private ProjectCreativeRepository projectCreativeRepository;
    @Autowired
    private ProjectCreativeDTOValidator projectCreativeDTOValidator;

    // ATTENZIONE BISOGNA AGGIUNGERE LE FOTO CHE SONO BELLE COMPLESSE.
    // LA LOGICA CHE CI STA DIETRO E'CHE IL FRONT INVIA UN DATO BLOB E IL BACK RITORNA L'IDENTIFICATORE E IL LINK.
    @Transactional
    public ProjectCreative updateProjectCreativeWithPatch(JwtAuthenticationToken auth, Long projectCreativeId, ProjectCreativeDTO projectCreativeDTO, BindingResult bindingResult) {
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // controlliamo se l'utente esiste
        ProfileCreative existingProfileCreative = profileCreativeRepository.findById(id).orElse(null);
        if (existingProfileCreative == null) {
            bindingResult.reject("notfound", "User not found.");
            return null;
        }

        // controlliamo se l'utente ha giá questo porgetto
        Optional<ProjectCreative> existingProjectCreativeOpt = projectCreativeRepository.findById(projectCreativeId);
        if (!existingProjectCreativeOpt.isPresent()) {
            bindingResult.reject("notfound", "Project not found.");
            return null;
        }

        // Estraendo il valore da Optional
        ProjectCreative existingProjectCreative = existingProjectCreativeOpt.get();

        // Collega il ProfileCreative al ProjectCreative
        existingProjectCreative.setProfileCreative(existingProfileCreative);

        // Crea un DataBinder e imposta il nome dell'oggetto
        DataBinder binder = new DataBinder(projectCreativeDTO, "projectCreativeDTO");
        binder.setValidator(projectCreativeDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            return null;
        }

        if (projectCreativeDTO.getName() != null) {
            existingProjectCreative.setName(projectCreativeDTO.getName().trim());
        }
        // FOTO
        if (projectCreativeDTO.getYear() != null) {
            existingProjectCreative.setYear(Short.valueOf(projectCreativeDTO.getYear().trim()));
        }
        if (projectCreativeDTO.getType() != null) {
            existingProjectCreative.setType(projectCreativeDTO.getType().trim());
        }
        if (projectCreativeDTO.getDescription() != null) {
            existingProjectCreative.setDescription(projectCreativeDTO.getDescription().trim());
        }
        if (projectCreativeDTO.getCustomer() != null) {
            existingProjectCreative.setCustomer(projectCreativeDTO.getCustomer().trim());
        }
        if (projectCreativeDTO.getLink() != null) {
            existingProjectCreative.setLink(projectCreativeDTO.getLink().trim());
        }

        return projectCreativeRepository.save(existingProjectCreative);
    }
}

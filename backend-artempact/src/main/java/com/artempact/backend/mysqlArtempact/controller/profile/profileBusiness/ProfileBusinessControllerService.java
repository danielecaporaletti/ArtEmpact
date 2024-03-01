package com.artempact.backend.mysqlArtempact.controller.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.ProfileBusinessDTO;
import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.junctionTable.CreativeSearchLocationsDTO;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.CreativeSearchLocations;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.ProfileBusinessRepository;
import com.artempact.backend.mysqlArtempact.validator.profile.profileBusiness.ProfileBusinessDTOValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ProfileBusinessControllerService {

    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;
    @Autowired
    private TypeOfBusinessRepository typeOfBusinessRepository;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;
    @Autowired
    private ProfileBusinessDTOValidator profileBusinessDTOValidator;

    @Transactional
    public ProfileBusiness updateProfileBusinessWithPatch(JwtAuthenticationToken auth, ProfileBusinessDTO profileBusinessDTO, BindingResult bindingResult) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        ProfileBusiness existingProfileBusiness = profileBusinessRepository.findById(id).orElse(null);
        if (existingProfileBusiness == null) {
            bindingResult.reject("notfound", "Utente non trovato");
            return null;
        }

        // Crea un DataBinder e imposta il nome dell'oggetto
        DataBinder binder = new DataBinder(profileBusinessDTO, "profileBusinessDTO");
        binder.setValidator(profileBusinessDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            return null;
        }

        if (profileBusinessDTO.getEmail() != null) {
            existingProfileBusiness.setEmail(profileBusinessDTO.getEmail().trim());
        }
        if (profileBusinessDTO.getPhone() != null) {
            existingProfileBusiness.setPhone(profileBusinessDTO.getPhone().trim());
        }
        if (profileBusinessDTO.getName() != null) {
            existingProfileBusiness.setName(profileBusinessDTO.getName().trim());
        }
        if (profileBusinessDTO.getSurname() != null) {
            existingProfileBusiness.setSurname(profileBusinessDTO.getSurname().trim());
        }
        if (profileBusinessDTO.getDob() != null) {
            existingProfileBusiness.setDob(LocalDate.parse(profileBusinessDTO.getDob().trim()));
        }
        if (profileBusinessDTO.getLocality() != null) {
            existingProfileBusiness.getLocality().setCity(profileBusinessDTO.getLocality().getCity().trim());
            existingProfileBusiness.getLocality().setProvince(profileBusinessDTO.getLocality().getProvince().trim());
            existingProfileBusiness.getLocality().setLat(profileBusinessDTO.getLocality().getLat());
            existingProfileBusiness.getLocality().setLon(profileBusinessDTO.getLocality().getLon());
        }
        if (profileBusinessDTO.getDescription() != null) {
            existingProfileBusiness.setDescription(profileBusinessDTO.getDescription().trim());
        }
        if (profileBusinessDTO.getMaxDistance() != null) {
            existingProfileBusiness.setMaxDistance(Short.valueOf(profileBusinessDTO.getMaxDistance().trim()));
        }
        if (profileBusinessDTO.getProfileCompletionPercentage() != null) {
            existingProfileBusiness.setProfileCompletionPercentage(Short.valueOf(profileBusinessDTO.getProfileCompletionPercentage().trim()));
        }
        if (profileBusinessDTO.getBusinessName() != null) {
            existingProfileBusiness.setBusinessName(profileBusinessDTO.getBusinessName().trim());
        }
        if (profileBusinessDTO.getTypeOfBusiness() != null) {
            TypeOfBusiness typeOfBusiness = typeOfBusinessRepository.findById(Short.valueOf(profileBusinessDTO.getTypeOfBusiness()))
                    .orElseThrow(() -> new EntityNotFoundException("TypeOfBusiness not found"));
            existingProfileBusiness.setTypeOfBusiness(typeOfBusiness);
        }
        if (profileBusinessDTO.getWorkPreference() != null) {
            WorkPreference workPreference = workPreferenceRepository.findById(Short.valueOf(profileBusinessDTO.getWorkPreference()))
                    .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"));
            existingProfileBusiness.setWorkPreference(workPreference);
        }
        if (profileBusinessDTO.getCreativeSearchLocations() != null) {
            Set<CreativeSearchLocations> currentLocations = existingProfileBusiness.getCreativeSearchLocations();

            for (CreativeSearchLocationsDTO locationDTO : profileBusinessDTO.getCreativeSearchLocations()) {
                // Controlla se la location esiste già
                boolean locationExists = currentLocations.stream()
                        .anyMatch(loc -> loc.getCity().equals(locationDTO.getCity()) && loc.getProvince().equals(locationDTO.getProvince()));

                // Se non esiste, aggiungila
                if (!locationExists) {
                    CreativeSearchLocations newLocation = convertToEntity(locationDTO, existingProfileBusiness);
                    currentLocations.add(newLocation);
                }
            }
        }

        return profileBusinessRepository.save(existingProfileBusiness);
    }

    private CreativeSearchLocations convertToEntity(CreativeSearchLocationsDTO dto, ProfileBusiness profileBusiness) {
        CreativeSearchLocations entity = new CreativeSearchLocations();
        entity.setCity(dto.getCity());
        entity.setProvince(dto.getProvince());
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());
        entity.setProfileBusiness(profileBusiness);
        return entity;
    }
}

package com.artempact.backend.mysqlArtempact.controller.profile.profileCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.ProfileCreativeDTO;
import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.junctionTable.JobSearchLocationDTO;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfCreative;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.junctionTables.JobSearchLocation;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.WorkPreferenceRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.validator.profile.profileCreative.ProfileCreativeDTOValidator;
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
public class ProfileCreativeControllerService {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private TypeOfCreativeRepository typeOfCreativeRepository;
    @Autowired
    private EducationTypeRepository educationTypeRepository;
    @Autowired
    private WorkPreferenceRepository workPreferenceRepository;
    @Autowired
    private ProfileCreativeDTOValidator profileCreativeDTOValidator;

    @Transactional
    public ProfileCreative updateProfileCreativeWithPatch(JwtAuthenticationToken auth, ProfileCreativeDTO profileCreativeDTO, BindingResult bindingResult) {
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        ProfileCreative existingProfileCreative = profileCreativeRepository.findById(id).orElse(null);
        if (existingProfileCreative == null) {
            bindingResult.reject("notfound", "Utente non trovato");
            return null;
        }

        // Crea un DataBinder e imposta il nome dell'oggetto
        DataBinder binder = new DataBinder(profileCreativeDTO, "profileCreativeDTO");
        binder.setValidator(profileCreativeDTOValidator);
        binder.validate();

        // Combina gli errori di validazione
        if (binder.getBindingResult().hasErrors()) {
            bindingResult.addAllErrors(binder.getBindingResult());
            return null;
        }

        if (profileCreativeDTO.getEmail() != null) {
            existingProfileCreative.setEmail(profileCreativeDTO.getEmail().trim());
        }
        if (profileCreativeDTO.getPhone() != null) {
            existingProfileCreative.setPhone(profileCreativeDTO.getPhone().trim());
        }
        if (profileCreativeDTO.getName() != null) {
            existingProfileCreative.setName(profileCreativeDTO.getName().trim());
        }
        if (profileCreativeDTO.getSurname() != null) {
            existingProfileCreative.setSurname(profileCreativeDTO.getSurname().trim());
        }
        if (profileCreativeDTO.getDob() != null) {
            existingProfileCreative.setDob(LocalDate.parse(profileCreativeDTO.getDob().trim()));
        }
        if (profileCreativeDTO.getLocality() != null) {
            existingProfileCreative.getLocality().setCity(profileCreativeDTO.getLocality().getCity().trim());
            existingProfileCreative.getLocality().setProvince(profileCreativeDTO.getLocality().getProvince().trim());
            existingProfileCreative.getLocality().setLat(profileCreativeDTO.getLocality().getLat());
            existingProfileCreative.getLocality().setLon(profileCreativeDTO.getLocality().getLon());
        }
        if (profileCreativeDTO.getDescription() != null) {
            existingProfileCreative.setDescription(profileCreativeDTO.getDescription().trim());
        }
        if (profileCreativeDTO.getMaxDistance() != null) {
            existingProfileCreative.setMaxDistance(Short.valueOf(profileCreativeDTO.getMaxDistance().trim()));
        }
        if (profileCreativeDTO.getProfileCompletionPercentage() != null) {
            existingProfileCreative.setProfileCompletionPercentage(Short.valueOf(profileCreativeDTO.getProfileCompletionPercentage().trim()));
        }
        if (profileCreativeDTO.getCreativeName() != null) {
            existingProfileCreative.setCreativeName(profileCreativeDTO.getCreativeName().trim());
        }
        if (profileCreativeDTO.getTypeOfCreative() != null) {
            TypeOfCreative typeOfCreative = typeOfCreativeRepository.findById(Short.valueOf(profileCreativeDTO.getTypeOfCreative()))
                    .orElseThrow(() -> new EntityNotFoundException("TypeOfCreative not found"));
            existingProfileCreative.setTypeOfCreative(typeOfCreative);
        }
        if (profileCreativeDTO.getSectorOfCompetence() != null) {
            existingProfileCreative.setSectorOfCompetence(profileCreativeDTO.getSectorOfCompetence().trim());
        }
        if (profileCreativeDTO.getEducationType() != null) {
            EducationType educationType = educationTypeRepository.findById(Short.valueOf(profileCreativeDTO.getEducationType()))
                    .orElseThrow(() -> new EntityNotFoundException("EducationType not found"));
            existingProfileCreative.setEducationType(educationType);
        }
        if (profileCreativeDTO.getFinalYearOfEducation() != null) {
            existingProfileCreative.setFinalYearOfEducation(Short.valueOf(profileCreativeDTO.getFinalYearOfEducation().trim()));
        }
        if (profileCreativeDTO.getWorkPreference() != null) {
            WorkPreference workPreference = workPreferenceRepository.findById(Short.valueOf(profileCreativeDTO.getWorkPreference()))
                    .orElseThrow(() -> new EntityNotFoundException("WorkPreference not found"));
            existingProfileCreative.setWorkPreference(workPreference);
        }
        if (profileCreativeDTO.getJobSearchLocations() != null) {
            Set<JobSearchLocation> currentLocations = existingProfileCreative.getJobSearchLocations();

            for (JobSearchLocationDTO locationDTO : profileCreativeDTO.getJobSearchLocations()) {
                // Controlla se la location esiste già
                boolean locationExists = currentLocations.stream()
                        .anyMatch(loc -> loc.getCity().equals(locationDTO.getCity()) && loc.getProvince().equals(locationDTO.getProvince()));

                // Se non esiste, aggiungila
                if (!locationExists) {
                    JobSearchLocation newLocation = convertToEntity(locationDTO, existingProfileCreative);
                    currentLocations.add(newLocation);
                }
            }
        }

        return profileCreativeRepository.save(existingProfileCreative);
    }

    private JobSearchLocation convertToEntity(JobSearchLocationDTO dto, ProfileCreative profileCreative) {
        JobSearchLocation entity = new JobSearchLocation();
        entity.setCity(dto.getCity());
        entity.setProvince(dto.getProvince());
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());
        entity.setProfileCreative(profileCreative);
        return entity;
    }
}

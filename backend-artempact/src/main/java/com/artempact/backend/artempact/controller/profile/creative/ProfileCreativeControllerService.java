package com.artempact.backend.artempact.controller.profile.creative;

import com.artempact.backend.artempact.controller.HandleAttributesUtility;
import com.artempact.backend.artempact.dto.profile.creative.ProfileCreativeDTO;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.entity.profile.creative.junction.CreativeCityTarget;
import com.artempact.backend.artempact.geoService.AsyncGeoService;
import com.artempact.backend.artempact.repository.repository.lookup.EducationTypeRepository;
import com.artempact.backend.artempact.repository.repository.lookup.TypeOfCreativeRepository;
import com.artempact.backend.artempact.repository.repository.lookup.WorkPreferenceRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.junction.CreativeCityLocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileCreativeControllerService {

    private final HandleAttributesUtility handleAttributesUtility;
    private final AsyncGeoService asyncGeoService;
    private final ProfileCreativeRepository profileCreativeRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private final EducationTypeRepository educationTypeRepository;
    private final CreativeCityLocationRepository creativeCityTargetRepository;

    @Transactional
    public ProfileCreative getService(String userId) {

        return profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));
    }

    @Transactional
    public ProfileCreative patchService(String userId, ProfileCreativeDTO profileCreativeDTO) {
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for id: " + userId));

        return saveOrUpdateProfileCreative(profileCreativeDTO, profileCreative);
    }

    private ProfileCreative saveOrUpdateProfileCreative(ProfileCreativeDTO dto, ProfileCreative entity) {
        // Omit the null properties in copy
        handleAttributesUtility.copyNonNullProperties(dto, entity);

        // ATTENTION, HERE WE DO NOT VALID THE DATE, IS ALREADY DONE IN VALIDATOR INSIDE THE DTO
        // Handle WorkPreference
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyWorkPreference()),
                entity::setIdentifyWorkPreference,
                workPreferenceRepository);

        // Handle TypeOfCreative
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyCreativeType()),
                entity::setIdentifyCreativeType,
                typeOfCreativeRepository);

        // handle EducationalType
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyEducationBackground()),
                entity::setIdentifyEducationBackground,
                educationTypeRepository);

        // Handle Locality
        if (dto.getLocality() != null) {
            ProfileCreative.Locality newLocality = new ProfileCreative.Locality();
            BeanUtils.copyProperties(dto.getLocality(), newLocality);
            entity.setLocality(newLocality);
        }

        // handle CityTarget
        if (dto.getCreativeCityTargets() != null && !dto.getCreativeCityTargets().isEmpty()) {
            dto.getCreativeCityTargets().forEach(creativeCityTargetDTO -> {
                Optional<CreativeCityTarget> existingTarget = entity.getCreativeCityTargets().stream()
                        .filter(ct -> ct.getCity().equals(creativeCityTargetDTO.getCity()) && ct.getProvince().equals(creativeCityTargetDTO.getProvince()))
                        .findFirst();
                if (!existingTarget.isPresent()) { // Se non esiste aggiungilo
                    CreativeCityTarget newCreativeCityTarget = new CreativeCityTarget();
                    BeanUtils.copyProperties(creativeCityTargetDTO, newCreativeCityTarget);
                    newCreativeCityTarget.setProfileCreative(entity); //Imposta il riferimento
                    entity.getCreativeCityTargets().add(newCreativeCityTarget); // Aggiungi alla collezione
                }
                // Se esiste, si presume che non sia necessario aggiornare
            });
        }

        profileCreativeRepository.save(entity);

        // Qui effettuiamo le chiamate asincrone per aggiornare le coordinate
        if (dto.getLocality() != null) {
            asyncGeoService.updateCoordinates(profileCreativeRepository, entity.getId(), dto.getLocality().getCity(), dto.getLocality().getProvince());
        }
        if (dto.getCreativeCityTargets() != null) {
            entity.getCreativeCityTargets().forEach(creativeCityTarget -> {
                asyncGeoService.updateCityTargetCoordinates(creativeCityTargetRepository, creativeCityTarget.getId(), creativeCityTarget.getCity(), creativeCityTarget.getProvince());
            });
        }

        return entity;
    }

}

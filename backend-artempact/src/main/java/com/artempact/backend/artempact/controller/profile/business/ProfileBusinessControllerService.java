package com.artempact.backend.artempact.controller.profile.business;

import com.artempact.backend.artempact.controller.HandleAttributesUtility;
import com.artempact.backend.artempact.dto.profile.business.ProfileBusinessDTO;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.artempact.backend.artempact.entity.profile.business.junction.BusinessCityTarget;
import com.artempact.backend.artempact.geoService.AsyncGeoService;
import com.artempact.backend.artempact.repository.repository.lookup.TypeOfBusinessRepository;
import com.artempact.backend.artempact.repository.repository.lookup.WorkPreferenceRepository;
import com.artempact.backend.artempact.repository.repository.profile.business.ProfileBusinessRepository;
import com.artempact.backend.artempact.repository.repository.profile.business.junction.BusinessCityLocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileBusinessControllerService {

    private final HandleAttributesUtility handleAttributesUtility;
    private final AsyncGeoService asyncGeoService;
    private final ProfileBusinessRepository profileBusinessRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final TypeOfBusinessRepository typeOfBusinessRepository;
    private final BusinessCityLocationRepository businessCityTargetRepository;


    @Transactional
    public ProfileBusiness getService(String userId) {

        return profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("profileBusiness not found for id: " + userId));
    }

    @Transactional
    public ProfileBusiness patchService(String userId, ProfileBusinessDTO profileBusinessDTO) {
        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileBusiness not found for id: " + userId));

        return saveOrUpdateProfileBusiness(profileBusinessDTO, profileBusiness);
    }

    private ProfileBusiness saveOrUpdateProfileBusiness(ProfileBusinessDTO dto,  ProfileBusiness entity) {
        // Omit the null properties in copy
        handleAttributesUtility.copyNonNullProperties(dto, entity);

        // ATTENTION, HERE WE DO NOT VALID THE DATEs, IS ALREADY DONE IN VALIDATOR INSIDE THE DTO
        // Handle WorkPreference
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyWorkPreference()),
                entity::setIdentifyWorkPreference,
                workPreferenceRepository);

        // Handle TypeOfBusiness
        handleAttributesUtility.handleAttributeWithRepository(
                Optional.ofNullable(dto.getIdentifyBusinessType()),
                entity::setIdentifyBusinessType,
                typeOfBusinessRepository);

        // Handle Locality
        if (dto.getLocality() != null) {
            ProfileBusiness.Locality newLocality = new ProfileBusiness.Locality();
            BeanUtils.copyProperties(dto.getLocality(), newLocality);
            entity.setLocality(newLocality);
        }

        // Handle CityTarget
        if (dto.getCityTargets() != null && !dto.getCityTargets().isEmpty()) {
            dto.getCityTargets().forEach(cityTargetDTO -> {
                Optional<BusinessCityTarget> existingTarget = entity.getBusinessCityTargets().stream()
                        .filter(ct -> ct.getCity().equals(cityTargetDTO.getCity()) && ct.getProvince().equals(cityTargetDTO.getProvince()))
                        .findFirst();

                if (!existingTarget.isPresent()) { // Se non esiste, aggiungilo
                    BusinessCityTarget newBusinessCityTarget = new BusinessCityTarget();
                    BeanUtils.copyProperties(cityTargetDTO, newBusinessCityTarget);
                    newBusinessCityTarget.setProfileBusiness(entity); // Imposta il riferimento
                    entity.getBusinessCityTargets().add(newBusinessCityTarget); // Aggiungi alla collezione
                }
                // Se esiste, si presume che non sia necessario aggiornare
            });
        }

        profileBusinessRepository.save(entity);

        // Chiamate asincrone per aggiornare le coordinate
        if (dto.getLocality() != null) {
            asyncGeoService.updateCoordinates(profileBusinessRepository, entity.getId(), dto.getLocality().getCity(), dto.getLocality().getProvince());
        }

        if (dto.getCityTargets() != null) {
            entity.getBusinessCityTargets().forEach(businessCityTarget -> {
                asyncGeoService.updateCityTargetCoordinates(businessCityTargetRepository, businessCityTarget.getId(), businessCityTarget.getCity(), businessCityTarget.getProvince());
            });
        }

        return entity;
    }

}

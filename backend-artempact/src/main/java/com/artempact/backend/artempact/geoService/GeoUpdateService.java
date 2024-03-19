package com.artempact.backend.artempact.geoService;

import com.artempact.backend.artempact.entity.LocalityInterface;
import com.artempact.backend.artempact.entity.profile.Profile;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericLocationRepository;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class GeoUpdateService {

    @Transactional
    public <T extends Profile> void updateProfileWithCoordinates(GenericProfileRepository<T, String> repository, String profileId, Map<String, Double> coordinates) {
        repository.findById(profileId).ifPresent(profile -> {
            profile.getLocality().setLat(coordinates.get("lat"));
            profile.getLocality().setLon(coordinates.get("lon"));
            repository.save(profile);
        });
    }

    @Transactional
    public <T extends LocalityInterface> void updateCityTargetWithCoordinates(GenericLocationRepository<T, Long> repository, Long cityTargetId, Map<String, Double> coordinates) {
        repository.findById(cityTargetId).ifPresent(cityTarget -> {
            cityTarget.setLat(coordinates.get("lat"));
            cityTarget.setLon(coordinates.get("lon"));
            repository.save(cityTarget);
        });
    }
}


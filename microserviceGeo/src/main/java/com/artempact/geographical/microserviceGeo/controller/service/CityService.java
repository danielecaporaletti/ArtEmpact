package com.artempact.geographical.microserviceGeo.controller.service;

import com.artempact.geographical.microserviceGeo.entityITA.Comune;
import com.artempact.geographical.microserviceGeo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<Comune> findByDenominazioneItaStartingWith(String prefix) {
        return cityRepository.findByDenominazioneItaStartingWithIgnoreCase(prefix);
    }

    public Optional<Comune> findByCityAndProvince(String city, String province) {
        return cityRepository.findByDenominazioneItaAndSiglaProvincia(city, province);
    }
}


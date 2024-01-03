package com.artempact.backend.mysqlGeographic.controller.service;

import com.artempact.backend.mysqlGeographic.reposity.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GeographicService {

    @Autowired
    private ComuneRepository comuneRepository;

    // Input city e province, output lat and lon
    public Optional<List<BigDecimal>> getLatAndLonFromCityAndProvince(String city, String province) {
        return comuneRepository.findLatLonByCittaAndProvincia(city, province)
                .stream()
                .findFirst()
                .map(latLon -> Arrays.asList((BigDecimal) latLon[0], (BigDecimal) latLon[1]));
    }
}





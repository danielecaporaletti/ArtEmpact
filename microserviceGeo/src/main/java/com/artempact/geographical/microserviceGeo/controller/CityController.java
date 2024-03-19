package com.artempact.geographical.microserviceGeo.controller;

import com.artempact.geographical.microserviceGeo.entityITA.Comune;
import com.artempact.geographical.microserviceGeo.controller.service.CityService;
import com.artempact.geographical.microserviceGeo.entityITA.ComuneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/search")
    public List<ComuneDTO> searchComuni(@RequestParam String prefix) {
        List<Comune> comuni = cityService.findByDenominazioneItaStartingWith(prefix);
        return comuni.stream()
                .map(comune -> new ComuneDTO(comune.getDenominazioneIta(), comune.getSiglaProvincia()))
                .collect(Collectors.toList());
    }

    @GetMapping("/coordinates")
    public ResponseEntity<?> getCoordinates(@RequestParam String city, @RequestParam String province) {
        return cityService.findByCityAndProvince(city, province)
                .map(comune -> Map.of("lat", comune.getLat(), "lon", comune.getLon()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

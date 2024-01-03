package com.artempact.backend.mysqlGeographic.controller;

import com.artempact.backend.mysqlGeographic.entity.Comune;
import com.artempact.backend.mysqlGeographic.entity.ComuneDTO;
import com.artempact.backend.mysqlGeographic.reposity.ComuneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comuni")
public class ComuneController {

    private final ComuneRepository comuneRepository;

    public ComuneController(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    @GetMapping("/namesByPrefix")
    public ResponseEntity<List<ComuneDTO>> searchByPrefix(@RequestParam String prefix) {
        List<Comune> comuni = comuneRepository.findComuneByDenominazioneItaStartingWith(prefix);
        List<ComuneDTO> dtoList = comuni.stream()
                .map(comune -> new ComuneDTO(comune.getDenominazioneIta(), comune.getSiglaProvincia()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}
package com.artempact.backend.mysqlGeographic.controller;

import com.artempact.backend.mysqlGeographic.reposity.ComuneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comuni")
public class ComuneController {

    private final ComuneRepository comuneRepository;

    public ComuneController(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    @GetMapping("/namesByPrefix")
    public ResponseEntity<List<String>> searchByDenominazione(@RequestParam String prefix) {
        List<String> result = comuneRepository.findDenominazioneItaByStartingWith(prefix);
        return ResponseEntity.ok(result);
    }
}
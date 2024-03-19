package com.artempact.backend.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessOpen {

    @GetMapping("/accessOpen")
    public String accessOpen() {
        return "Questo é il backend aperto";
    }
}

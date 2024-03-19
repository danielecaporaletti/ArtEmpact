package com.artempact.backend.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessClose {

    @GetMapping("/accessClose")
    public String accessClose() {
        return "Questo é il backend chiuso";
    }
}

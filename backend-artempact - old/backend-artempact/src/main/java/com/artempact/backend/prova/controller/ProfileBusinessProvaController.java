package com.artempact.backend.prova.controller;

import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.ProfileBusinessDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.prova.service.ProfileBusinessProvaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class ProfileBusinessProvaController {

    @Autowired
    private ProfileBusinessProvaService profileBusinessProvaService;

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProfileBusiness(@PathVariable String id,
                                                   @Valid @RequestBody ProfileBusinessDTO profileBusinessDTO,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Raccogli i messaggi di errore
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        // Aggiorna l'utente
        ProfileBusiness updatedProfileBusiness = profileBusinessProvaService.updateProfileBusinessWithPatch(id, profileBusinessDTO);
        return ResponseEntity.ok(updatedProfileBusiness);
    }
}

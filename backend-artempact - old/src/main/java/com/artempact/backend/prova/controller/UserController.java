package com.artempact.backend.prova.controller;

import com.artempact.backend.prova.dto.UserPatchDTO;
import com.artempact.backend.prova.entity.User;
import com.artempact.backend.prova.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usersaaaaaaa")
public class UserController {

    @Autowired
    private UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody UserPatchDTO userUpdateDTO,
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
        User updatedUser = userService.updateUserWithPatch(id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }
}

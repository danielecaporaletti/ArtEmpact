package com.artempact.backend.mysqlArtempact.controller.profile.profileCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.ProfileCreativeDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile/creative")
public class ProfileCreativeController {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private ProfileCreativeControllerService profileCreativeControllerService;

    @GetMapping
    public ResponseEntity<ProfileCreative> getProfileCreative(JwtAuthenticationToken auth) {

        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        Optional<ProfileCreative> profileCreative = profileCreativeRepository.findById(id);

        if (profileCreative.isPresent()) {
            return ResponseEntity.ok(profileCreative.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateProfileCreative(JwtAuthenticationToken auth, @Valid @RequestBody ProfileCreativeDTO profileCreativeDTO, BindingResult bindingResult) {
        ProfileCreative updatedProfileCreative = profileCreativeControllerService.updateProfileCreativeWithPatch(auth, profileCreativeDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(updatedProfileCreative);
    }
}
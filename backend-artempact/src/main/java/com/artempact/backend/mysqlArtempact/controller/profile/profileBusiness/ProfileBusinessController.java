package com.artempact.backend.mysqlArtempact.controller.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.ProfileBusinessDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.ProfileBusinessRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile/business")
public class ProfileBusinessController {

    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;
    @Autowired
    private ProfileBusinessControllerService profileBusinessControllerService;

    @GetMapping
    public ResponseEntity<ProfileBusiness> getProfileBusiness(JwtAuthenticationToken auth) {

        // Estrarre l'ID dell'utente corrente
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        Optional<ProfileBusiness> profileBusiness = profileBusinessRepository.findById(id);

        if (profileBusiness.isPresent()) {
            return ResponseEntity.ok(profileBusiness.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateProfileBusiness(JwtAuthenticationToken auth, @Valid @RequestBody ProfileBusinessDTO profileBusinessDTO, BindingResult bindingResult) {
        ProfileBusiness updatedProfileBusiness = profileBusinessControllerService.updateProfileBusinessWithPatch(auth, profileBusinessDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(updatedProfileBusiness);
    }
}

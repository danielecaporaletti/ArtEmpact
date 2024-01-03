package com.artempact.backend.mysqlArtempact.controller.profileBusiness;

import com.artempact.backend.mysqlArtempact.controller.service.PatchGenericService;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.ProfileBusinessRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/profile/business")
public class ProfileBusinessController {

    private final ProfileBusinessRepository profileBusinessRepository;
    private final PatchGenericService patchGenericService;

    public ProfileBusinessController(ProfileBusinessRepository profileBusinessRepository, PatchGenericService patchGenericService) {
        this.profileBusinessRepository = profileBusinessRepository;
        this.patchGenericService = patchGenericService;
    }

    // Ritorna il profilo dell'utente (business) corrente
    @GetMapping
    public ResponseEntity<ProfileBusiness> getProfileBusiness(JwtAuthenticationToken auth) {

        // Estrarre l'ID dell'utente corrente
        final var id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        Optional<ProfileBusiness> profileBusiness = profileBusinessRepository.findById(id);

        if(profileBusiness.isPresent()) {
            return ResponseEntity.ok(profileBusiness.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo PATCH per aggiornare parzialmente un profilo business
    @PatchMapping
    public ResponseEntity<?> patchProfileBusiness(JwtAuthenticationToken auth, @RequestBody Map<String, Object> updates) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return profileBusinessRepository.findById(id)
                .map(profile -> patchGenericService.updateEntity(profile, updates, ProfileBusiness.class, profileBusinessRepository::save,auth))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

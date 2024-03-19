package com.artempact.backend.artempact.controller.profile.creative;

import com.artempact.backend.artempact.dto.profile.creative.ProfileCreativeDTO;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/creative")
@RequiredArgsConstructor
public class ProfileCreativeController {

    private final ProfileCreativeControllerService profileCreativeControllerService;
    private final ProfileCreativeRepository profileCreativeRepository;

    @GetMapping
    public ResponseEntity<ProfileCreative> getProfileCreative(JwtAuthenticationToken auth) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(profileCreativeControllerService.getService(userId), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ProfileCreative> updateProfileCreative(JwtAuthenticationToken auth, @Valid @RequestBody ProfileCreativeDTO profileCreativeDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        return new ResponseEntity<>(profileCreativeControllerService.patchService(userId, profileCreativeDTO), HttpStatus.ACCEPTED);
    }

}

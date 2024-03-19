package com.artempact.backend.artempact.controller.profile.business;

import com.artempact.backend.artempact.dto.profile.business.ProfileBusinessDTO;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.artempact.backend.artempact.repository.repository.profile.business.ProfileBusinessRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/business")
@RequiredArgsConstructor
public class ProfileBusinessController {

    private final ProfileBusinessControllerService profileBusinessControllerService;

    @GetMapping
    public ResponseEntity<ProfileBusiness> getProfileBusiness(JwtAuthenticationToken auth) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(profileBusinessControllerService.getService(userId), HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity<ProfileBusiness> updateProfileBusiness(JwtAuthenticationToken auth, @Valid @RequestBody ProfileBusinessDTO profileBusinessDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        return new ResponseEntity<>(profileBusinessControllerService.patchService(userId, profileBusinessDTO), HttpStatus.ACCEPTED);
    }

}

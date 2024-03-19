package com.artempact.backend.artempact.controller.profile.business.junction;

import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.artempact.backend.artempact.repository.repository.profile.business.ProfileBusinessRepository;
import com.artempact.backend.artempact.repository.repository.profile.business.junction.BusinessCityLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/business/businessCityTarget")
@RequiredArgsConstructor
public class BusinessCityTargetController {

    private final ProfileBusinessRepository profileBusinessRepository;
    private final BusinessCityLocationRepository businessCityTargetRepository;

    @DeleteMapping
    public ResponseEntity<?> deleteBusinessCityTargetById(JwtAuthenticationToken auth, @RequestParam("id") Long businessCityTargetId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElse(null);
        if (profileBusiness == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova la BusinessCityTarget basandosi sull'ID
        return businessCityTargetRepository.findById(businessCityTargetId)
                .map(businessCityTarget -> {
                    // Controlla se l'utente corrente è il proprietario della CreativeSearchLocation
                    if (businessCityTarget.getProfileBusiness().getId().equals(userId)) {
                        // Elimina la CreativeSearchLocation
                        businessCityTargetRepository.delete(businessCityTarget);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario, quindi ritorna un errore
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this BusinessCityTarget");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("BusinessCityTarget not found"));
    }
}

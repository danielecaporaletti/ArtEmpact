package com.artempact.backend.artempact.controller.profile.creative.junction;

import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.junction.CreativeCityLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/creative/creativeCityTarget")
@RequiredArgsConstructor
public class CreativeCityTargetController {

    private final ProfileCreativeRepository profileCreativeRepository;
    private final CreativeCityLocationRepository creativeCityTargetRepository;

    @DeleteMapping
    public ResponseEntity<?> deleteCreativeCityTargetById(JwtAuthenticationToken auth, @RequestParam("id") Long creativeCityTargetId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElse(null);
        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova la JobSearchLocation basandosi sull'ID
        return creativeCityTargetRepository.findById(creativeCityTargetId)
                .map(creativeCityTarget -> {
                    // Controlla se l'utente corrente è il proprietario della JobSearchLocation
                    if (creativeCityTarget.getProfileCreative().getId().equals(userId)) {
                        // Elimina la creativeCityTarget
                        creativeCityTargetRepository.delete(creativeCityTarget);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario, quindi ritorna un errore
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this CreativeCityTarget");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("CreativeCityTarget not found"));
    }
}

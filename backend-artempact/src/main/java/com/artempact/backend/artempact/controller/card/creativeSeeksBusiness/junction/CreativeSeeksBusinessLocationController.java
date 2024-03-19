package com.artempact.backend.artempact.controller.card.creativeSeeksBusiness.junction;

import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocation;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksBusiness.CreativeSeeksBusinessRepository;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocationRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/creative/creativeSeeksBusiness/creativeSeeksBusinessLocations")
@RequiredArgsConstructor
public class CreativeSeeksBusinessLocationController {

    private final ProfileCreativeRepository profileCreativeRepository;
    private final CreativeSeeksBusinessLocationRepository creativeSeeksBusinessLocationRepository;

    @DeleteMapping
    public ResponseEntity<?> deleteCreativeSeeksBusinessLocation(JwtAuthenticationToken auth, @RequestParam("id") Long locationId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Verifica l'esistenza del profilo creativo per l'ID utente
        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ProfileCreative not found for the current user"));

        // Cerca la location da eliminare e verifica che appartenga all'utente corrente
        CreativeSeeksBusinessLocation location = creativeSeeksBusinessLocationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("CreativeSeeksBusinessLocation not found for the given ID"));

        // Verifica che la location appartenga a uno dei CreativeSeeksBusiness del ProfileCreative corrente
        if (!location.getCreativeSeeksBusiness().getProfileCreative().getId().equals(profileCreative.getId())) {
            // Se la location non appartiene al profilo creativo corrente, restituisci un errore
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The Location does not belong to the current user");
        }

        // Elimina la location
        creativeSeeksBusinessLocationRepository.delete(location);

        // Restituisci una risposta di successo
        return ResponseEntity.ok().build();
    }
}

package com.artempact.backend.mysqlArtempact.controller.card.creativeSeeksBusiness.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocationRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/creative/creativeSeeksBusiness/locationCard")
public class CreativeSeeksBusinessLocationController {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private CreativeSeeksBusinessLocationRepository creativeSeeksBusinessLocationRepository;

    @DeleteMapping("/{creativeSeeksBusinessLocationCardId}")
    public ResponseEntity<?> deleteCreativeSeeksBusinessLocationById(JwtAuthenticationToken auth, @PathVariable Long creativeSeeksBusinessLocationCardId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElse(null);
        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova la jobSearchLocationCardId basandosi sull'ID
        return creativeSeeksBusinessLocationRepository.findById(creativeSeeksBusinessLocationCardId)
                .map(jobSearchLocationCard -> {
                    // Controlla se l'utente corrente è il proprietario della jobSearchLocationCardId
                    if (jobSearchLocationCard.getCreativeSeeksBusiness().getProfileCreative().getId().equals(userId)) {
                        // Elimina la jobSearchLocationCardId
                        creativeSeeksBusinessLocationRepository.delete(jobSearchLocationCard);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario, quindi ritorna un errore
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this jobSearchLocationCardId");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("jobSearchLocationCardId not found"));
    }
}

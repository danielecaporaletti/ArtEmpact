package com.artempact.backend.mysqlArtempact.controller.profile.profileBusiness.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.ProfileBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.junctionTables.CreativeSearchLocationRepository;
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
@RequestMapping("/profile/business/creativeSearchLocation")
public class CreativeSearchLocationController {

    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;
    @Autowired
    private CreativeSearchLocationRepository creativeSearchLocationRepository;

    @DeleteMapping("/{creativeSearchLocationId}")
    public ResponseEntity<?> deleteCreativeSearchLocationById(JwtAuthenticationToken auth, @PathVariable Long creativeSearchLocationId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileBusiness profileBusiness = profileBusinessRepository.findById(userId)
                .orElse(null);
        if (profileBusiness == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova la CreativeSearchLocation basandosi sull'ID
        return creativeSearchLocationRepository.findById(creativeSearchLocationId)
                .map(creativeSearchLocation -> {
                    // Controlla se l'utente corrente è il proprietario della CreativeSearchLocation
                    if (creativeSearchLocation.getProfileBusiness().getId().equals(userId)) {
                        // Elimina la CreativeSearchLocation
                        creativeSearchLocationRepository.delete(creativeSearchLocation);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario, quindi ritorna un errore
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this CreativeSearchLocation");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("CreativeSearchLocation not found"));
    }
}

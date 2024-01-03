package com.artempact.backend.mysqlArtempact.controller.profile.profileCreative.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.junctionTables.JobSearchLocationRepository;
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
@RequestMapping("/profile/creative/jobSearchLocation")
public class JobSearchLocationController {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private JobSearchLocationRepository jobSearchLocationRepository;

    @DeleteMapping("/{jobSearchLocationId}")
    public ResponseEntity<?> deleteJobSearchLocationById(JwtAuthenticationToken auth, @PathVariable Long jobSearchLocationId) {
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        ProfileCreative profileCreative = profileCreativeRepository.findById(userId)
                .orElse(null);
        if (profileCreative == null) {
            return ResponseEntity.notFound().build();
        }

        // Trova la JobSearchLocation basandosi sull'ID
        return jobSearchLocationRepository.findById(jobSearchLocationId)
                .map(jobSearchLocation -> {
                    // Controlla se l'utente corrente è il proprietario della JobSearchLocation
                    if (jobSearchLocation.getProfileCreative().getId().equals(userId)) {
                        // Elimina la JobSearchLocation
                        jobSearchLocationRepository.delete(jobSearchLocation);
                        return ResponseEntity.ok().build();
                    } else {
                        // L'utente non è il proprietario, quindi ritorna un errore
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not own this JobSearchLocation");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("JobSearchLocation not found"));
    }
}

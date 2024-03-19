package com.artempact.backend.artempact.controller.card.creativeSeeksCollaboration;

import com.artempact.backend.artempact.dto.card.creativeSeeksCollaboration.CreativeSeeksCollaborationDTO;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/profile/business/creativeSeeksCollaboration")
@RequiredArgsConstructor
public class CreativeSeeksCollaborationController {

    private final CreativeSeeksCollaborationService creativeSeeksCollaborationService;

    @GetMapping
    public ResponseEntity<Set<CreativeSeeksCollaboration>> getCreativeSeeksCollaboration(JwtAuthenticationToken auth) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(creativeSeeksCollaborationService.getService(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreativeSeeksCollaboration> createCreativeSeeksCollaboration(JwtAuthenticationToken auth, @Valid @RequestBody CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(creativeSeeksCollaborationService.postService(userId, creativeSeeksCollaborationDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<CreativeSeeksCollaboration> updateCreativeSeeksCollaboration(JwtAuthenticationToken auth, @RequestParam("id") Long cardId, @Valid @RequestBody CreativeSeeksCollaborationDTO creativeSeeksCollaborationDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(creativeSeeksCollaborationService.patchService(userId, cardId, creativeSeeksCollaborationDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCreativeSeeksCollaboration(JwtAuthenticationToken auth, @RequestParam("id") Long cardId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        creativeSeeksCollaborationService.deleteService(userId, cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

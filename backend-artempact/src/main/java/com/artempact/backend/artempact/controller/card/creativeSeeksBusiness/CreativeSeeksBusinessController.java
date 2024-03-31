package com.artempact.backend.artempact.controller.card.creativeSeeksBusiness;

import com.artempact.backend.artempact.dto.card.creativeSeeksBusiness.CreativeSeeksBusinessDTO;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/profile/creative/creativeSeeksBusiness")
@RequiredArgsConstructor
public class CreativeSeeksBusinessController {

    private final CreativeSeeksBusinessService creativeSeeksBusinessService;

    @GetMapping
    public ResponseEntity<Set<CreativeSeeksBusiness>> getCreativeSeeksBusiness(JwtAuthenticationToken auth) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(creativeSeeksBusinessService.getService(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreativeSeeksBusiness> createCreativeSeeksBusiness(JwtAuthenticationToken auth, @Valid @RequestBody CreativeSeeksBusinessDTO creativeSeeksBusinessDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(creativeSeeksBusinessService.postService(userId, creativeSeeksBusinessDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<CreativeSeeksBusiness> updateCreativeSeeksBusiness(JwtAuthenticationToken auth, @RequestParam("id") Long cardId, @Valid @RequestBody CreativeSeeksBusinessDTO creativeSeeksBusinessDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(creativeSeeksBusinessService.patchService(userId, cardId, creativeSeeksBusinessDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCreativeSeeksBusiness(JwtAuthenticationToken auth, @RequestParam("id") Long cardId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        creativeSeeksBusinessService.deleteService(userId, cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.artempact.backend.artempact.controller.card.businessSeeksCreative;

import com.artempact.backend.artempact.dto.card.businessSeeksCreative.BusinessSeeksCreativeDTO;
import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/profile/business/businessSeeksCreative")
@RequiredArgsConstructor
public class BusinessSeeksCreativeController {

    private final BusinessSeeksCreativeService businessSeeksCreativeService;

    @GetMapping
    public ResponseEntity<Set<BusinessSeeksCreative>> getBusinessSeeksCreative(JwtAuthenticationToken auth) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(businessSeeksCreativeService.getService(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BusinessSeeksCreative> createBusinessSeeksCreative(JwtAuthenticationToken auth, @Valid @RequestBody BusinessSeeksCreativeDTO businessSeeksCreativeDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(businessSeeksCreativeService.postService(userId, businessSeeksCreativeDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<BusinessSeeksCreative> updateBusinessSeeksCreative(JwtAuthenticationToken auth, @RequestParam("id") Long cardId, @Valid @RequestBody BusinessSeeksCreativeDTO businessSeeksCreativeDTO) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        return new ResponseEntity<>(businessSeeksCreativeService.patchService(userId, cardId, businessSeeksCreativeDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBusinessSeeksCreative(JwtAuthenticationToken auth, @RequestParam("id") Long cardId) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        businessSeeksCreativeService.deleteService(userId, cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

package com.artempact.backend.artempact.algorithm;

import com.artempact.backend.artempact.algorithm.entity.CompatibilityCard;
import com.artempact.backend.artempact.repository.repository.CompatibilityCardRepository;
import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.artempact.repository.repository.card.businessSeeksCreative.BusinessSeeksCreativeRepository;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksBusiness.CreativeSeeksBusinessRepository;
import com.artempact.backend.artempact.repository.repository.card.creativeSeeksCollaboration.CreativeSeeksCollaborationRepository;
import com.artempact.backend.artempact.repository.repository.profile.business.ProfileBusinessRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class CompatibilityController {

    private static final int MAX_ATTEMPTS = 20;
    private final CompatibilityService compatibilityService;
    private final ProfileCreativeRepository profileCreativeRepository;
    private final ProfileBusinessRepository profileBusinessRepository;
    private final BusinessSeeksCreativeRepository businessSeeksCreativeRepository;
    private final CreativeSeeksCollaborationRepository creativeSeeksCollaborationRepository;
    private final CreativeSeeksBusinessRepository creativeSeeksBusinessRepository;
    private final CompatibilityCardRepository compatibilityCardRepository;

    @GetMapping("/nextCard")
    public ResponseEntity<?> createMatch(JwtAuthenticationToken auth) {
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        String userType = auth.getToken().getClaimAsString("user_type");

        System.out.println("USERTYPE:" + userType);
        Optional<MatchResult> matchResult;

        switch (userType) {
            case "creative":
                matchResult = findCompatibilityForCreative(userId);
                break;
            case "business":
                matchResult = findCompatibilityForBusiness(userId);
                break;
            default:
                // Gestione di un caso di userType non supportato o sconosciuto
                return ResponseEntity.badRequest().body("User type '" + userType + "' is not supported.");
        }

        return matchResult
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/resultCompatibility")
    public ResponseEntity<?> saveCompatibility(@RequestBody MatchResult matchResult, @RequestParam("isCompatible") boolean isCompatible) {

        Long fromCardId = extractId(matchResult.getMyCard());
        Long toCardId = extractId(matchResult.getCompatibilityCard());

        if (fromCardId == null || toCardId == null) {
            return ResponseEntity.badRequest().body("Invalid card IDs");
        }

        CompatibilityCard compatibilityCard = new CompatibilityCard();
        compatibilityCard.setFromCardIdName(matchResult.getMyCardName());
        compatibilityCard.setFromCardId(fromCardId);
        compatibilityCard.setToCardIdName(matchResult.getCompatibilityCardName());
        compatibilityCard.setToCardId(toCardId);
        compatibilityCard.setIsCompatible(isCompatible);
        compatibilityCard.setTimestamp(LocalDateTime.now());

        compatibilityCardRepository.save(compatibilityCard);

        // Cerca tutte le corrispondenze da A a B
        List<CompatibilityCard> matchesFromAtoB = compatibilityCardRepository.findByFromCardId(fromCardId);
        // Cerca tutte le corrispondenze da B a A
        List<CompatibilityCard> matchesFromBtoA = compatibilityCardRepository.findByToCardId(toCardId);

        // Controlla se esiste almeno una coppia di record che indica una compatibilità reciproca
        boolean mutualMatchFound = matchesFromAtoB.stream().anyMatch(cardA ->
                matchesFromBtoA.stream().anyMatch(cardB ->
                        cardA.getFromCardId().equals(cardB.getToCardId()) && cardA.getToCardId().equals(cardB.getFromCardId())
                )
        );

        if (mutualMatchFound) {
            return ResponseEntity.ok().body("It's a Match");
        }

        return ResponseEntity.ok().build();
    }

    private Long extractId(Object card) {
        if (card instanceof Map) {
            Map<?, ?> cardMap = (Map<?, ?>) card;
            Number id = (Number) cardMap.get("id");
            return id != null ? id.longValue() : null;
        }
        // Gestire altri tipi se necessario
        return null;
    }

    private Optional<MatchResult> findCompatibilityForCreative(String userId) {
        return profileCreativeRepository.findById(userId).flatMap(profileCreative -> {
            for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
                BusinessSeeksCreative randomBusinessSeeksCreative = businessSeeksCreativeRepository.findRandom();
                if (randomBusinessSeeksCreative != null) {
                    MatchResult compatibilityResult = compatibilityService.isCompatibilityFromCreativeSeeksBusiness(profileCreative.getMaxDistance(), profileCreative.getCreativeSeeksBusinesses(), randomBusinessSeeksCreative);
                    if (compatibilityResult != null && compatibilityResult.getMyCard() != null && compatibilityResult.getCompatibilityCard() != null) {
                        return Optional.of(compatibilityResult);
                    }
                }

                CreativeSeeksCollaboration randomCreativeSeeksCollaboration = creativeSeeksCollaborationRepository.findRandom();
                if (randomCreativeSeeksCollaboration != null) {
                    MatchResult compatibilityResult = compatibilityService.isCompatibilityFromCreativeSeeksCollaboration(profileCreative.getMaxDistance(), profileCreative.getCreativeSeeksCollaborations(), randomCreativeSeeksCollaboration);
                    if (compatibilityResult != null && compatibilityResult.getMyCard() != null && compatibilityResult.getCompatibilityCard() != null) {
                        return Optional.of(compatibilityResult);
                    }
                }
            }
            return Optional.empty();
        });
    }

    private Optional<MatchResult> findCompatibilityForBusiness(String userId) {
        return profileBusinessRepository.findById(userId).flatMap(profileBusiness -> {
            for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
                CreativeSeeksBusiness randomCreativeSeeksBusiness = creativeSeeksBusinessRepository.findRandom();
                if (randomCreativeSeeksBusiness != null) {
                    System.out.println("Ho trovato randomicamente questa: " + randomCreativeSeeksBusiness.getId());
                    MatchResult compatibilityResult = compatibilityService.isCompatibilityFromBusinessSeeksCreative(
                            profileBusiness.getMaxDistance(),
                            profileBusiness.getBusinessSeeksCreatives(),
                            randomCreativeSeeksBusiness);

                    if (compatibilityResult != null && compatibilityResult.getMyCard() != null && compatibilityResult.getCompatibilityCard() != null) {
                        return Optional.of(compatibilityResult);
                    }
                }
            }
            return Optional.empty();
        });
    }





}

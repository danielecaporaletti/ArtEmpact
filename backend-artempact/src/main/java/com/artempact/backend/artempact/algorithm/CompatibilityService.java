package com.artempact.backend.artempact.algorithm;

import com.artempact.backend.artempact.algorithm.entity.CompatibilityCard;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocation;
import com.artempact.backend.artempact.repository.repository.CompatibilityCardRepository;
import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.junction.CreativeSeeksCollaborationLocation;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompatibilityService {

    private final Algorithm algorithm;
    private final CompatibilityCardRepository compatibilityCardRepository;

    /*
    // Prendi le coordinate del BusinessSeeksCreative (le prendo dal profilo business, solo solo una)
    // Crea uno Stream di tutte le coordinate dentro a creativeSeeksBusiness
    // isValid (Vedere se c'é almeno un match tra le posizioni coordinate)
     */
    public MatchResult isCompatibilityFromBusinessSeeksCreative(Short maxDistance, Set<BusinessSeeksCreative> businessSeeksCreatives, CreativeSeeksBusiness creativeSeeksBusiness) {
        for (BusinessSeeksCreative bsc : businessSeeksCreatives) {
            ProfileBusiness profileBusiness = bsc.getProfileBusiness();
            if (profileBusiness == null || profileBusiness.getLocality() == null) {
                continue; // Il profilo business non esiste o non ha una località definita.
            }

            // Verifica se esiste già un record di compatibilità tra l'entità specifica BusinessSeeksCreative e CreativeSeeksBusiness
            Optional<CompatibilityCard> existingCompatibility = compatibilityCardRepository.findByFromCardIdAndToCardId(bsc.getId(), creativeSeeksBusiness.getId());
            if (existingCompatibility.isPresent()) {
                continue; // Se esiste già una compatibilità (positiva o negativa), skip questo BusinessSeeksCreative.
            }

            double x1 = profileBusiness.getLocality().getLat();
            double y1 = profileBusiness.getLocality().getLon();

            for (CreativeSeeksBusinessLocation location : creativeSeeksBusiness.getCreativeSeeksBusinessLocations()) {
                Algorithm.DistanceValidationResult validationResult =
                        algorithm.isValid((double) maxDistance, x1, y1, location.getLat(), location.getLon());

                if (validationResult.isValid()) {
                    // Costruisci e restituisci un CompatibilityResult con la distanza calcolata
                    return new MatchResult("BusinessSeeksCreative", "CreativeSeeksBusiness",
                            validationResult.getDistance(), bsc, creativeSeeksBusiness);
                }
            }
        }
        // Restituisci un risultato che indica che non è stata trovata alcuna compatibilità
        return new MatchResult("BusinessSeeksCreative", "CreativeSeeksBusiness",
                 0, null, null); // Usare valori null o un costruttore diverso per indicare l'assenza di compatibilità
    }

    /*
    // Crea uno stream delle coordinate del CreativeSeeksBusiness
    // Prendi le coordinate del BusinessSeeksCreative (le prendo dal profilo business, solo solo una)
    // isValid (Vedere se c'é almeno un match tra le posizioni coordinate)
     */
    public MatchResult  isCompatibilityFromCreativeSeeksBusiness(Short maxDistance, Set<CreativeSeeksBusiness> creativeSeeksBusinessSet, BusinessSeeksCreative businessSeeksCreative) {
        ProfileBusiness profileBusiness = businessSeeksCreative.getProfileBusiness();
        if (profileBusiness == null || profileBusiness.getLocality() == null) {
            // Considera di restituire un MatchResult che indichi l'assenza di compatibilità
            return new MatchResult("CreativeSeeksBusiness", "BusinessSeeksCreative", 0, null, null);
        }

        double businessLat = profileBusiness.getLocality().getLat();
        double businessLon = profileBusiness.getLocality().getLon();

        // Itera su ogni CreativeSeeksBusiness nel set
        for (CreativeSeeksBusiness csb : creativeSeeksBusinessSet) {
            // Controlla se esiste già un record di compatibilità tra l'entità specifica CreativeSeeksBusiness e BusinessSeeksCreative
            Optional<CompatibilityCard> existingCompatibility = compatibilityCardRepository.findByFromCardIdAndToCardId(csb.getId(), businessSeeksCreative.getId());
            if (existingCompatibility.isPresent()) {
                continue; // Se esiste già una compatibilità (positiva o negativa), skip questo CreativeSeeksBusiness.
            }

            // Per ogni CreativeSeeksBusiness, itera sulle sue località
            for (CreativeSeeksBusinessLocation location : csb.getCreativeSeeksBusinessLocations()) {
                Algorithm.DistanceValidationResult validationResult = algorithm.isValid((double) maxDistance, location.getLat(), location.getLon(), businessLat, businessLon);
                if (validationResult.isValid()) {
                    // Restituisci un MatchResult con la distanza calcolata e le informazioni delle card
                    return new MatchResult("CreativeSeeksBusiness", "BusinessSeeksCreative", validationResult.getDistance(), csb, businessSeeksCreative);
                }
            }
        }
        // Nessun oggetto CreativeSeeksBusiness compatibile trovato
        return new MatchResult("CreativeSeeksBusiness", "BusinessSeeksCreative", 0, null, null);
    }


    /*
    // Crea uno stream delle coordinate del creativeSeeksCollaboration
    // Crea uno stream delle coordinate del creativeSeeksCollaboration
    // isValid (Vedere se c'é almeno un match tra le posizioni coordinate)
     */
    public MatchResult isCompatibilityFromCreativeSeeksCollaboration(Short maxDistance, Set<CreativeSeeksCollaboration> creativeSeeksCollaborationSet, CreativeSeeksCollaboration creativeSeeksCollaboration2) {
        // Itera su ogni CreativeSeeksCollaboration nel set
        for (CreativeSeeksCollaboration csc1 : creativeSeeksCollaborationSet) {
            // Controlla se esiste già un record di compatibilità tra le due entità CreativeSeeksCollaboration
            Optional<CompatibilityCard> existingCompatibility = compatibilityCardRepository.findByFromCardIdAndToCardId(csc1.getId(), creativeSeeksCollaboration2.getId());
            if (existingCompatibility.isPresent()) {
                continue; // Se esiste già una compatibilità (positiva o negativa), skip questo controllo.
            }

            // Comntrolla che csc1 non sia la stessa creativeSeeksCollaboration2
            if (csc1.getId().equals(creativeSeeksCollaboration2.getId())) {
                continue;
            }

            // Controlla se c'è almeno una coppia di località che soddisfa il criterio di match
            for (CreativeSeeksCollaborationLocation loc1 : csc1.getCreativeSeeksCollaborationLocations()) {
                for (CreativeSeeksCollaborationLocation loc2 : creativeSeeksCollaboration2.getCreativeSeeksCollaborationLocations()) {
                    Algorithm.DistanceValidationResult validationResult = algorithm.isValid((double) maxDistance, loc1.getLat(), loc1.getLon(), loc2.getLat(), loc2.getLon());
                    if (validationResult.isValid()) {
                        // Se trova una coppia di località compatibile, restituisce un MatchResult con i dettagli
                        return new MatchResult("CreativeSeeksCollaboration", "CreativeSeeksCollaboration", validationResult.getDistance(), csc1, creativeSeeksCollaboration2);
                    }
                }
            }
        }

        // Se nessuna coppia di località è compatibile, restituisce un MatchResult che indica l'assenza di compatibilità
        return new MatchResult("CreativeSeeksCollaboration", "CreativeSeeksCollaboration", 0, null, null);
    }


}

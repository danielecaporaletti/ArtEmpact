package com.artempact.backend.artempact.repository.repository.card.creativeSeeksBusiness;

import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericCardRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreativeSeeksBusinessRepository extends GenericCardRepository<CreativeSeeksBusiness, Long> {

    @Query(value = "SELECT * FROM creative_seeks_business ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CreativeSeeksBusiness findRandom();
}

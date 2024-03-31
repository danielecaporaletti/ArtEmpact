package com.artempact.backend.artempact.repository.repository.card.businessSeeksCreative;

import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericCardRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessSeeksCreativeRepository extends GenericCardRepository<BusinessSeeksCreative, Long> {

    @Query(value = "SELECT * FROM business_seeks_creative ORDER BY RAND() LIMIT 1", nativeQuery = true)
    BusinessSeeksCreative findRandom();
}

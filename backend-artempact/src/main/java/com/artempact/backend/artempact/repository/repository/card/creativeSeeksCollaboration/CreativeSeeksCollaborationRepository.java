package com.artempact.backend.artempact.repository.repository.card.creativeSeeksCollaboration;

import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericCardRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreativeSeeksCollaborationRepository extends GenericCardRepository<CreativeSeeksCollaboration, Long> {

    @Query(value = "SELECT * FROM creative_seeks_collaboration ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CreativeSeeksCollaboration findRandom();
}

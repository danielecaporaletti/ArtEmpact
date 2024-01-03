package com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineProfessionalRelationship", types = { ProfessionalRelationship.class })
public interface InlineProfessionalRelationship {

    Short getId();

    String getProfessionalRelationshipName();
}

package com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineEducationType", types = { EducationType.class })
public interface InlineEducationType {

    Short getId();

    String getEducationName();
}


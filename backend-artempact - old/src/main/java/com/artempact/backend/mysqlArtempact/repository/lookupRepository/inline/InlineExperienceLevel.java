package com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineExperienceLevel", types = { ExperienceLevel.class })
public interface InlineExperienceLevel {

    Short getId();

    String getExperienceName();
}

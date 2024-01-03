package com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineWorkPreference", types = { WorkPreference.class })
public interface InlineWorkPreference {

    Short getId();

    String getWorkPreferenceName();
}

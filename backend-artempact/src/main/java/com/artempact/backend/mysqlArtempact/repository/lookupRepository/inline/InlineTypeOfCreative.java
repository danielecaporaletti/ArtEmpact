package com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfCreative;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineTypeOfCreative", types = { TypeOfCreative.class })
public interface InlineTypeOfCreative {

    Short getId();

    String getTypeName();
}

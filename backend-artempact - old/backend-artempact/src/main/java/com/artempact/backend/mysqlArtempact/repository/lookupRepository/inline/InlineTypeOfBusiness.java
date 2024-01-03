package com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineTypeOfBusiness", types = { TypeOfBusiness.class })
public interface InlineTypeOfBusiness {

    Short getId();

    String getTypeName();
}

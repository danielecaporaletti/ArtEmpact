package com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness;

import com.artempact.backend.mysqlArtempact.dto.card.CardDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocationDTO;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreativeSeeksBusinessDTO extends CardDTO {
    private String positionDescription;
    private String identifyBusinessType;
    private Set<CreativeSeeksBusinessLocationDTO> creativeSeeksBusinessLocations;
}

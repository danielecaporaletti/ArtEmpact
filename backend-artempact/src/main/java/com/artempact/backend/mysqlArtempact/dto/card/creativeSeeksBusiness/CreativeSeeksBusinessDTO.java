package com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness;

import com.artempact.backend.mysqlArtempact.dto.card.CardDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.junctionTable.JobSearchLocationCardDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreativeSeeksBusinessDTO extends CardDTO {
    private String positionDescription;
    private String identifyBusinesType;
    private Set<JobSearchLocationCardDTO> jobSearchLocationCards;
}

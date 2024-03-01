package com.artempact.backend.mysqlArtempact.dto.card.businessSeeksCreative;

import com.artempact.backend.mysqlArtempact.dto.card.CardDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessSeeksCreativeDTO extends CardDTO {
    private String companyVisionMission;
    private String identifyCreativeType;
}

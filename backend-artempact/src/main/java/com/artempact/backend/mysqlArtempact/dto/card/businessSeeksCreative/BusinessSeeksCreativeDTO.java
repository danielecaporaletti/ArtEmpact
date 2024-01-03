package com.artempact.backend.mysqlArtempact.dto.card.businessSeeksCreative;

import com.artempact.backend.mysqlArtempact.dto.card.CardDTO;
import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessSeeksCreativeDTO extends CardDTO {

    @Getter
    @Setter
    @Embeddable
    public static class Locality implements LocationInterface {
        private String city;
        private String province;
        private Double lat;
        private Double lon;
    }
    private String companyVisionMission;
    private String identifyCreativeType;
    @Embedded
    private BusinessSeeksCreativeDTO.Locality locality;
}

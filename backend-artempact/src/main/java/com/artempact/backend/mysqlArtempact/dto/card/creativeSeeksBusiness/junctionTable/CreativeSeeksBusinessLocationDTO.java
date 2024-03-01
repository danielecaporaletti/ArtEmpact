package com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksBusiness.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreativeSeeksBusinessLocationDTO implements LocationInterface {
    private String city;
    private String province;
    private Double lat;
    private Double lon;
    private CreativeSeeksBusiness creativeSeeksBusiness;
}

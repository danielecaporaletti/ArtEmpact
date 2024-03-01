package com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksCollaboration.junctiontable;

import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreativeSeeksCollaborationLocationDTO implements LocationInterface {
    private String city;
    private String province;
    private Double lat;
    private Double lon;
    private CreativeSeeksBusiness creativeSeeksBusiness;
}

package com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreativeSearchLocationsDTO implements LocationInterface {
    private String city;
    private String province;
    private Double lat;
    private Double lon;
    private ProfileBusiness profileBusiness;
}

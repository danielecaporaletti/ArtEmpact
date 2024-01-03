package com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSearchLocationDTO implements LocationInterface {
    private String city;
    private String province;
    private Double lat;
    private Double lon;
    private ProfileCreative profileCreative;
}

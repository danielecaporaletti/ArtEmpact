package com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.dto.profile.ProfileDTO;
import com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness.junctionTable.CreativeSearchLocationsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProfileBusinessDTO extends ProfileDTO {
    private String businessName;
    private String typeOfBusiness;
    private String workPreference;
    // DA FARE
    // private Set<PhotoBusiness> photosBusiness;
    private Set<CreativeSearchLocationsDTO> creativeSearchLocations;
}

package com.artempact.backend.mysqlArtempact.dto.profile.profileCreative;

import com.artempact.backend.mysqlArtempact.dto.profile.ProfileDTO;
import com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.junctionTable.JobSearchLocationDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProfileCreativeDTO extends ProfileDTO {
    private String creativeName;
    private String typeOfCreative;
    private String sectorOfCompetence;
    private String educationType;
    private String finalYearOfEducation;
    private String workPreference;
    // DA FARE
    // private Set<PhotoCreative> photoCreatives;
    private Set<JobSearchLocationDTO> jobSearchLocations;

}

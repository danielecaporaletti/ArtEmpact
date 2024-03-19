package com.artempact.backend.artempact.dto.profile.creative;

import com.artempact.backend.artempact.dto.ProfileDTO;
import com.artempact.backend.artempact.dto.profile.creative.junction.CreativeCityTargetDTO;
import com.artempact.backend.artempact.validator.ValidId;
import com.artempact.backend.artempact.validator.ValidYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProfileCreativeDTO extends ProfileDTO {
    @NotBlank(message = "The creativeName cannot be empty")
    @Size(max = 100, message = "The creativeName exceed 100 characters")
    private String creativeName;
    @ValidId(value = "typeOfCreative")
    private Short identifyCreativeType;
    @NotBlank(message = "The sectorOfCompetence cannot be empty")
    @Size(max = 100, message = "The sectorOfCompetence exceed 100 characters")
    private String sectorOfCompetence;
    @ValidId(value = "educationType")
    private Short identifyEducationBackground;
    @ValidYear(message = "The finalYearOfEducation must be between 1990 and the current year")
    private Short finalYearOfEducation;
    private Set<CreativeCityTargetDTO> creativeCityTargets;
}

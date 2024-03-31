package com.artempact.backend.artempact.dto.profile.business;

import com.artempact.backend.artempact.dto.ProfileDTO;
import com.artempact.backend.artempact.dto.profile.business.junction.BusinessCityTargetDTO;
import com.artempact.backend.artempact.validator.ValidId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProfileBusinessDTO extends ProfileDTO {
    @Size(max = 100, message = "The businessName exceed 100 characters")
    private String businessName;
    @ValidId(value = "typeOfBusiness")
    private Short identifyBusinessType;
    private Set<BusinessCityTargetDTO> cityTargets;
}

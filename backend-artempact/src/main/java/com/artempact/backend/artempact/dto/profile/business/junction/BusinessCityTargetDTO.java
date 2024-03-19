package com.artempact.backend.artempact.dto.profile.business.junction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessCityTargetDTO {
    @NotBlank(message = "The city cannot be empty")
    @Size(max = 100, message = "The city exceed 100 characters")
    private String city;
    @NotBlank(message = "The province cannot be empty")
    @Size(max = 2, message = "The province exceed 2 characters")
    private String province;
}

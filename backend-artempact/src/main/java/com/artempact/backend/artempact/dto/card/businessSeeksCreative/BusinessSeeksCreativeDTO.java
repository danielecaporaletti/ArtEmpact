package com.artempact.backend.artempact.dto.card.businessSeeksCreative;

import com.artempact.backend.artempact.dto.card.CardDTO;
import com.artempact.backend.artempact.validator.ValidId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessSeeksCreativeDTO extends CardDTO {
    @NotBlank(message = "The companyVisionMission cannot be empty")
    @Size(max = 280, message = "The companyVisionMission exceed 280 characters")
    private String companyVisionMission;
    @ValidId(value = "typeOfCreative")
    private Short identifyCreativeType;
}

package com.artempact.backend.artempact.dto.card.creativeSeeksBusiness;

import com.artempact.backend.artempact.dto.card.CardDTO;
import com.artempact.backend.artempact.dto.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocationDTO;
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
public class CreativeSeeksBusinessDTO extends CardDTO {
    @NotBlank(message = "The positionDescription cannot be empty")
    @Size(max = 280, message = "The positionDescription exceed 280 characters")
    private String positionDescription;
    @ValidId(value = "typeOfBusiness")
    private Short identifyBusinessType;
    private Set<CreativeSeeksBusinessLocationDTO> creativeSeeksBusinessLocations;
}

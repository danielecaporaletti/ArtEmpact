package com.artempact.backend.artempact.dto.card.creativeSeeksCollaboration;

import com.artempact.backend.artempact.dto.card.CardDTO;
import com.artempact.backend.artempact.dto.card.creativeSeeksCollaboration.junction.CreativeSeeksCollaborationLocationDTO;
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
public class CreativeSeeksCollaborationDTO extends CardDTO {
    @NotBlank(message = "The personalVisionMission cannot be empty")
    @Size(max = 280, message = "The personalVisionMission exceed 280 characters")
    private String personalVisionMission;
    @ValidId(value = "typeOfCreative")
    private Short identifyCreativeType;
    private Set<CreativeSeeksCollaborationLocationDTO> creativeSeeksCollaborationLocations;
}

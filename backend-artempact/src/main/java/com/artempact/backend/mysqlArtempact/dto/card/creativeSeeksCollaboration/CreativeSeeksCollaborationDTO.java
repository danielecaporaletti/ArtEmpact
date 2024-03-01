package com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksCollaboration;

import com.artempact.backend.mysqlArtempact.dto.card.CardDTO;
import com.artempact.backend.mysqlArtempact.dto.card.creativeSeeksCollaboration.junctiontable.CreativeSeeksCollaborationLocationDTO;
import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration.junctionTable.CreativeSeeksCollaborationLocation;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreativeSeeksCollaborationDTO extends CardDTO {
    private String personalVisionMission;
    private String identifyCreativeType;
    private Set<CreativeSeeksCollaborationLocationDTO> creativeSeeksCollaborationLocations;
}

package com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration;

import com.artempact.backend.mysqlArtempact.entity.card.Card;
import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable.CreativeSeeksBusinessLocation;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration.junctionTable.CreativeSeeksCollaborationLocation;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.*;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "CreativeSeeksCollaboration")
public class CreativeSeeksCollaboration extends Card {

    @NotBlank(message = "The personalVisionMission cannot be empty")
    @Size(max = 280, message = "The personalVisionMission exceeds 280 characters")
    @Column(name = "personalVisionMission", nullable = false, length = 280)
    private String personalVisionMission;

    //`@ManyToOne` indica che molti `CreativeSeeksCollaboration` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identifyCreativeType_id", referencedColumnName = "id")
    private TypeOfCreative identifyCreativeType;

    // `@OneToMany` indica che un singolo `CreativeSeeksCollaboration` può essere associato a molte `CreativeSeeksCollaborationLocation`
    @OneToMany(mappedBy = "creativeSeeksCollaboration", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CreativeSeeksCollaborationLocation> creativeSeeksCollaborationLocations;

    // `@ManyToOne` indica che molti `CreativeSeeksCollaboration` possono essere associate a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;

    public CreativeSeeksCollaboration(String title, String description, Integer setMinProjectBudget, Integer setMaxProjectBudget, String cardColor, EducationType educationalBackground, ExperienceLevel experienceLevel, ProfessionalRelationship professionalRelationship, String personalVisionMission, TypeOfCreative identifyCreativeType, Set<CreativeSeeksCollaborationLocation> creativeSeeksCollaborationLocations, WorkPreference workPreference) {
        super(title, description, setMinProjectBudget, setMaxProjectBudget, cardColor, educationalBackground, experienceLevel, professionalRelationship, workPreference);
        this.personalVisionMission = personalVisionMission;
        this.identifyCreativeType = identifyCreativeType;
        this.creativeSeeksCollaborationLocations = creativeSeeksCollaborationLocations;
    }
}

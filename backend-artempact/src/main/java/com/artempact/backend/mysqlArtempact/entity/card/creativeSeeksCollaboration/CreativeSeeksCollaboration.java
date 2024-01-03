package com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration;

import com.artempact.backend.mysqlArtempact.entity.card.Card;
import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfCreative;
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

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "CreativeSeeksCollaboration")
public class CreativeSeeksCollaboration extends Card {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class Locality implements LocationInterface {
        @NotBlank(message = "The city cannot be empty")
        @Size(max = 100, message = "The city exceeds 100 characters")
        @Column(name = "city", nullable = false, length = 100)
        private String city;

        @NotBlank(message = "The province cannot be empty")
        @Size(max = 2, message = "The province exceeds 2 characters")
        @Column(name = "province", nullable = false, length = 2)
        private String province;

        @JsonIgnore
        @NotNull(message = "The latitude cannot be empty")
        @Column(name = "lat", nullable = false)
        private Double lat;

        @JsonIgnore
        @NotNull(message = "The longitude cannot be empty")
        @Column(name = "lon", nullable = false)
        private Double lon;
    }

    @NotBlank(message = "The personalVisionMission cannot be empty")
    @Size(max = 280, message = "The personalVisionMission exceeds 280 characters")
    @Column(name = "personalVisionMission", nullable = false, length = 280)
    private String personalVisionMission;

    //`@ManyToOne` indica che molti `CreativeSeeksCollaboration` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identifyCreativeType_id", referencedColumnName = "id")
    private TypeOfCreative identifyCreativeType;

    @Embedded
    private CreativeSeeksCollaboration.Locality locality;

    // `@ManyToOne` indica che molti `CreativeSeeksCollaboration` possono essere associate a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;

    public CreativeSeeksCollaboration(String title, String description, Integer setMinProjectBudget, Integer setMaxProjectBudget, String cardColor, EducationType educationalBackground, ExperienceLevel experienceLevel, ProfessionalRelationship professionalRelationship, String personalVisionMission, TypeOfCreative identifyCreativeType, CreativeSeeksCollaboration.Locality locality) {
        super(title, description, setMinProjectBudget, setMaxProjectBudget, cardColor, educationalBackground, experienceLevel, professionalRelationship);
        this.personalVisionMission = personalVisionMission;
        this.identifyCreativeType = identifyCreativeType;
        this.locality = locality;
    }
}

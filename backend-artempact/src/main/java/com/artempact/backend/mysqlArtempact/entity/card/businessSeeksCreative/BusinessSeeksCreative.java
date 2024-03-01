package com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative;

import com.artempact.backend.mysqlArtempact.entity.card.Card;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.*;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "BusinessSeeksCreative")
public class BusinessSeeksCreative extends Card {

    @NotBlank(message = "The companyVisionMission cannot be empty")
    @Size(max = 280, message = "The companyVisionMission exceeds 280 characters")
    @Column(name = "companyVisionMission", nullable = false, length = 280)
    private String companyVisionMission;

    //`@ManyToOne` indica che molti `BusinessSeeksCreative` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identifyCreativeType_id", referencedColumnName = "id")
    private TypeOfCreative identifyCreativeType;

    // `@ManyToOne` indica che molti `BusinessSeeksCreative` possono essere associate a un singolo `ProfileBusiness`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_business_id")
    private ProfileBusiness profileBusiness;

    public BusinessSeeksCreative(String title, String description, Integer setMinProjectBudget, Integer setMaxProjectBudget, String cardColor, EducationType educationalBackground, ExperienceLevel experienceLevel, ProfessionalRelationship professionalRelationship, String companyVisionMission, TypeOfCreative identifyCreativeType, WorkPreference workPreference) {
        super(title, description, setMinProjectBudget, setMaxProjectBudget, cardColor, educationalBackground, experienceLevel, professionalRelationship, workPreference);
        this.companyVisionMission = companyVisionMission;
        this.identifyCreativeType = identifyCreativeType;
    }
}

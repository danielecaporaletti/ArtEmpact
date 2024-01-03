package com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness;

import com.artempact.backend.mysqlArtempact.entity.card.Card;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable.JobSearchLocationCard;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "CreativeSeeksBusiness")
public class CreativeSeeksBusiness extends Card {

    @NotBlank(message = "The positionDescription cannot be empty")
    @Size(max = 280, message = "The positionDescription exceeds 280 characters")
    @Column(name = "positionDescription", nullable = false, length = 280)
    private String positionDescription;

    //`@ManyToOne` indica che molti `CreativeSeeksBusiness` possono essere associati a un singolo `TypeOfBusiness`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identifyBusinesType_id", referencedColumnName = "id")
    private TypeOfBusiness identifyBusinesType;

    // `@OneToMany` indica che un singolo `CreativeSeeksBusiness` può essere associato a molte `JobSearchLocationCard`
    @OneToMany(mappedBy = "creativeSeeksBusiness", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobSearchLocationCard> jobSearchLocationCards;

    // `@ManyToOne` indica che molti `CreativeSeeksBusiness` possono essere associate a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;

    public CreativeSeeksBusiness(String title, String description, Integer setMinProjectBudget, Integer setMaxProjectBudget, String cardColor, EducationType educationalBackground, ExperienceLevel experienceLevel, ProfessionalRelationship professionalRelationship, String positionDescription, TypeOfBusiness identifyBusinesType, Set<JobSearchLocationCard> jobSearchLocationCards) {
        super(title, description, setMinProjectBudget, setMaxProjectBudget, cardColor, educationalBackground, experienceLevel, professionalRelationship);
        this.positionDescription = positionDescription;
        this.identifyBusinesType = identifyBusinesType;
        this.jobSearchLocationCards = jobSearchLocationCards;
    }
}

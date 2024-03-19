package com.artempact.backend.artempact.entity.card.businessSeeksCreative;

import com.artempact.backend.artempact.entity.card.Card;
import com.artempact.backend.artempact.entity.lookup.TypeOfCreative;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "artempact", name = "BusinessSeeksCreative")
public class BusinessSeeksCreative extends Card {

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
}

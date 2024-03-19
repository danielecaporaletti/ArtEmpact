package com.artempact.backend.artempact.entity.card.creativeSeeksBusiness;

import com.artempact.backend.artempact.entity.card.Card;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.junction.CreativeSeeksBusinessLocation;
import com.artempact.backend.artempact.entity.lookup.TypeOfBusiness;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "artempact", name = "CreativeSeeksBusiness")
public class CreativeSeeksBusiness extends Card {

    @Column(name = "positionDescription", nullable = false, length = 280)
    private String positionDescription;

    //`@ManyToOne` indica che molti `CreativeSeeksBusiness` possono essere associati a un singolo `TypeOfBusiness`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identifyBusinessType_id", referencedColumnName = "id")
    private TypeOfBusiness identifyBusinessType;

    // `@OneToMany` indica che un singolo `CreativeSeeksBusiness` può essere associato a molte `CreativeSeeksBusinessLocation`
    @OneToMany(mappedBy = "creativeSeeksBusiness", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CreativeSeeksBusinessLocation> creativeSeeksBusinessLocations;

    // `@ManyToOne` indica che molti `CreativeSeeksBusiness` possono essere associate a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;
}

package com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration;

import com.artempact.backend.artempact.entity.card.Card;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.junction.CreativeSeeksCollaborationLocation;
import com.artempact.backend.artempact.entity.lookup.TypeOfCreative;
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
@Table(schema = "artempact", name = "CreativeSeeksCollaboration")
public class CreativeSeeksCollaboration extends Card {

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
}

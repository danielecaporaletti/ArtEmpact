package com.artempact.backend.artempact.entity.lookup;

import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "artempact", name = "TypeOfCreative")
public class TypeOfCreative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @Column(name = "typeName", nullable = false, length = 100)
    private String typeName;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyCreativeType")
    private Set<ProfileCreative> profileCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyCreativeType")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyCreativeType")
    private Set<CreativeSeeksCollaboration> creativeSeeksCollaborations;

    public TypeOfCreative(String typeName) {
        this.typeName = typeName;
    }
}

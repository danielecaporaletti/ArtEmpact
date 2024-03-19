package com.artempact.backend.artempact.entity.lookup;

import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
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
@Table(schema = "artempact", name = "ExperienceLevel")
public class ExperienceLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @Column(name = "typeName ", nullable = false, length = 100)
    private String experienceName;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyExperienceLevel")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyExperienceLevel")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyExperienceLevel")
    private Set<CreativeSeeksCollaboration> creativeSeeksCollaborations;

    public ExperienceLevel(String experienceName) {
        this.experienceName = experienceName;
    }
}

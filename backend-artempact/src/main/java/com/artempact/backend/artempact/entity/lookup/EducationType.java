package com.artempact.backend.artempact.entity.lookup;

import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
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
@Table(schema = "artempact", name = "EducationType")
public class EducationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @Column(name = "typeName", nullable = false, length = 100)
    private String educationName ;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyEducationBackground")
    private Set<ProfileCreative> profileCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyEducationBackground")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyEducationBackground")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyEducationBackground")
    private Set<CreativeSeeksCollaboration> creativeSeeksCollaborations;

    public EducationType(String educationName) {
        this.educationName = educationName;
    }
}

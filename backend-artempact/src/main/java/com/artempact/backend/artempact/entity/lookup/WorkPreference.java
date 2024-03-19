package com.artempact.backend.artempact.entity.lookup;

import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
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
@Table(schema = "artempact", name = "WorkPreference")
public class WorkPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @Column(name = "workPreferenceName ", nullable = false, length = 100)
    private String workPreferenceName ;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyWorkPreference")
    private Set<ProfileBusiness> profileBusiness;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyWorkPreference")
    private Set<ProfileCreative> profileCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyWorkPreference")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyWorkPreference")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyWorkPreference")
    private Set<CreativeSeeksCollaboration> creativeSeeksCollaborations;

    public WorkPreference(String workPreferenceName) {
        this.workPreferenceName = workPreferenceName;
    }
}

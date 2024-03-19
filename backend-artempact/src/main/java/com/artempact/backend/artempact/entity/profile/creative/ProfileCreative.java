package com.artempact.backend.artempact.entity.profile.creative;

import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.artempact.entity.lookup.WorkPreference;
import com.artempact.backend.artempact.entity.lookup.EducationType;
import com.artempact.backend.artempact.entity.lookup.TypeOfCreative;
import com.artempact.backend.artempact.entity.profile.Profile;
import com.artempact.backend.artempact.entity.profile.creative.junction.CreativeCityTarget;
import com.artempact.backend.artempact.entity.profile.creative.project.ProjectCreative;
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
@Table(schema = "artempact", name = "ProfileCreative")
public class ProfileCreative extends Profile {

    @Column(name = "creativeName", nullable = false, length = 100)
    private String creativeName;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfCreative_id", referencedColumnName = "id")
    private TypeOfCreative identifyCreativeType;

    @Column(name = "sectorOfCompetence", length = 100)
    private String sectorOfCompetence;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `EducationType`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "educationType_id", referencedColumnName = "id")
    private EducationType identifyEducationBackground;

    @Column(name = "finalYearOfEducation", columnDefinition = "SMALLINT")
    private Short finalYearOfEducation;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `JobSearchLocation`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CreativeCityTarget> creativeCityTargets;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molti `ProjectCreative`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ProjectCreative> projectCreatives;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molti `CreativeSeeksBusiness`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molti `CreativeSeeksCollaboration`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER)
    private Set<CreativeSeeksCollaboration> creativeSeeksCollaborations;



}

package com.artempact.backend.artempact.entity.card;

import com.artempact.backend.artempact.entity.lookup.EducationType;
import com.artempact.backend.artempact.entity.lookup.ExperienceLevel;
import com.artempact.backend.artempact.entity.lookup.ProfessionalRelationship;
import com.artempact.backend.artempact.entity.lookup.WorkPreference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "title", nullable = false, length = 55)
    private String title;
    @Column(name = "description", nullable = false, length = 280)
    private String description;
    @Column(name = "minProjectBudget", columnDefinition = "INTEGER")
    private Integer minProjectBudget;
    @Column(name = "maxProjectBudget", columnDefinition = "INTEGER")
    private Integer maxProjectBudget;
    @Column(name = "cardColor", nullable = false, length = 2)
    private String cardColor;

    //`@ManyToOne` indica che molte `card` possono essere associati a un singolo `EducationType`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "educationType_id", referencedColumnName = "id")
    private EducationType identifyEducationBackground;

    //`@ManyToOne` indica che molte `card` possono essere associati a un singolo `ExperienceLevel`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "experienceLevel_id", referencedColumnName = "id")
    private ExperienceLevel identifyExperienceLevel;

    //`@ManyToOne` indica che molte `card` possono essere associati a un singolo `ProfessionalRelationship`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professionalRelationship_id", referencedColumnName = "id")
    private ProfessionalRelationship identifyProfessionalRelationship;

    //`@ManyToOne` indica che molti `card` possono essere associati a un singolo `WorkPreference`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workPreference_id", referencedColumnName = "id")
    private WorkPreference identifyWorkPreference;

    @Column(name = "video1", length = 280)
    private String video1;
}

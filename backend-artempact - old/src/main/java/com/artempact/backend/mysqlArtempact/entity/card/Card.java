package com.artempact.backend.mysqlArtempact.entity.card;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "The title cannot be empty")
    @Size(max = 55, message = "The title exceeds 55 characters")
    @Column(name = "email", nullable = false, length = 55)
    private String title;

    @Size(max = 1000, message = "The video link exceeds 1000 characters")
    @Column(name = "video", length = 1000)
    private String video;

    @NotBlank(message = "The description cannot be empty")
    @Size(max = 280, message = "The description exceeds 280 characters")
    @Column(name = "description", nullable = false, length = 280)
    private String description;

    @NotNull(message = "The setMinProjectBudget cannot be empty")
    @Min(value = 0, message = "The setMinProjectBudget have to be positive")
    @Column(name = "setMinProjectBudget", columnDefinition = "INTEGER")
    private Integer setMinProjectBudget;

    @NotNull(message = "The setMaxProjectBudget cannot be empty")
    @Min(value = 0, message = "The setMaxProjectBudget have to be positive")
    @Column(name = "setMaxProjectBudget", columnDefinition = "INTEGER")
    private Integer setMaxProjectBudget;

    @NotBlank(message = "The cardColor cannot be empty")
    @Size(min = 7, max = 7, message = "The cardColor HEX must be (#RRGGBB)")
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Invalid HEX color format (#RRGGBB)")
    @Column(name = "cardColor", nullable = false, length = 7)
    private String cardColor;

    //`@ManyToOne` indica che molte `card` possono essere associati a un singolo `EducationType`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "educationType_id", referencedColumnName = "id")
    private EducationType educationalBackground;

    //`@ManyToOne` indica che molte `card` possono essere associati a un singolo `ExperienceLevel`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "experienceLevel_id", referencedColumnName = "id")
    private ExperienceLevel experienceLevel;

    //`@ManyToOne` indica che molte `card` possono essere associati a un singolo `ProfessionalRelationship`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professionalRelationship_id", referencedColumnName = "id")
    private ProfessionalRelationship professionalRelationship;

    public Card(String title, String description, Integer setMinProjectBudget, Integer setMaxProjectBudget, String cardColor, EducationType educationalBackground, ExperienceLevel experienceLevel, ProfessionalRelationship professionalRelationship) {
        this.title = title;
        this.description = description;
        this.setMinProjectBudget = setMinProjectBudget;
        this.setMaxProjectBudget = setMaxProjectBudget;
        this.cardColor = cardColor;
        this.educationalBackground = educationalBackground;
        this.experienceLevel = experienceLevel;
        this.professionalRelationship = professionalRelationship;
    }
}

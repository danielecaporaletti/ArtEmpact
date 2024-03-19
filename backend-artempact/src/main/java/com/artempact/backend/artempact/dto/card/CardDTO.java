package com.artempact.backend.artempact.dto.card;

import com.artempact.backend.artempact.validator.ValidId;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class CardDTO {
    @NotBlank(message = "The title cannot be empty")
    @Size(max = 55, message = "The title exceed 55 characters")
    private String title;
    @NotBlank(message = "The description cannot be empty")
    @Size(max = 280, message = "The description exceed 280 characters")
    private String description;
    private Integer minProjectBudget;
    private Integer maxProjectBudget;
    private String cardColor;
    @ValidId(value = "educationType")
    private Short identifyEducationBackground;
    @ValidId(value = "experienceLevel")
    private Short identifyExperienceLevel;
    @ValidId(value = "professionalRelationship")
    private Short identifyProfessionalRelationship;
    @ValidId(value = "workPreference")
    private Short identifyWorkPreference;
}

package com.artempact.backend.mysqlArtempact.dto.card;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class CardDTO {
    private String title;
    private String description;
    private String minProjectBudget;
    private String maxProjectBudget;
    private String cardColor;
    private String educationalBackground;
    private String experienceLevel;
    private String professionalRelationship;
    private String workPreference;
    private String video1;
}

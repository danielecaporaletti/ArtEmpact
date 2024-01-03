package com.artempact.backend.mysqlArtempact.entity.lookupEntity;

import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "ExperienceLevel")
public class ExperienceLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @NotBlank(message = "The ExperienceLevel cannot be empty")
    @Size(max = 100, message = "The ExperienceLevel exceeds 100 characters")
    @Column(name = "typeName ", nullable = false, length = 100)
    private String experienceName;

    public ExperienceLevel(String experienceName ) {
        this.experienceName = experienceName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "experienceLevel")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "experienceLevel")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;
}

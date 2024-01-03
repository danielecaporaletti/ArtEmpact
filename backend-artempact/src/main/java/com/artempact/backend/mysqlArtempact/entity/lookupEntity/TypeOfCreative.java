package com.artempact.backend.mysqlArtempact.entity.lookupEntity;

import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
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
@Table(schema = "artempact", name = "TypeOfCreative")
public class TypeOfCreative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @NotBlank(message = "The typeOfCreative cannot be empty")
    @Size(max = 100, message = "The typeOfCreative exceeds 100 characters")
    @Column(name = "typeName", nullable = false, length = 100)
    private String typeName;

    public TypeOfCreative(String typeName ) {
        this.typeName = typeName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "typeOfCreative")
    private Set<ProfileCreative> profileCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyCreativeType")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;
}

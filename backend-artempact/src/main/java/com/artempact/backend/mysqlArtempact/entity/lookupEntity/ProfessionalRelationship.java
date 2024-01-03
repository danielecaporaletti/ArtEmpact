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
@Table(schema = "artempact", name = "ProfessionalRelationship")
public class ProfessionalRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @NotBlank(message = "The ProfessionalRelationship cannot be empty")
    @Size(max = 100, message = "The ProfessionalRelationship exceeds 100 characters")
    @Column(name = "typeName ", nullable = false, length = 100)
    private String professionalRelationshipName;

    public ProfessionalRelationship(String professionalRelationshipName ) {
        this.professionalRelationshipName = professionalRelationshipName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "professionalRelationship")
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    @JsonIgnore
    @OneToMany(mappedBy = "professionalRelationship")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;


}

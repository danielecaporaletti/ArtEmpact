package com.artempact.backend.mysqlArtempact.entity.lookupEntity;

import com.artempact.backend.mysqlArtempact.entity.card.creativeSeekBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
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
@Table(schema = "artempact", name = "TypeOfBusiness")
public class TypeOfBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @NotBlank(message = "The typeOfCreative cannot be empty")
    @Size(max = 100, message = "The typeOfCreative exceeds 100 characters")
    @Column(name = "typeName ", nullable = false, length = 100)
    private String typeName ;

    public TypeOfBusiness(String typeName ) {
        this.typeName = typeName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "typeOfBusiness")
    private Set<ProfileBusiness> profileBusiness;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyBusinesType")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;
}

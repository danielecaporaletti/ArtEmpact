package com.artempact.backend.artempact.entity.lookup;

import com.artempact.backend.artempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
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
@Table(schema = "artempact", name = "TypeOfBusiness")
public class TypeOfBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @Column(name = "typeName ", nullable = false, length = 100)
    private String typeName ;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyBusinessType")
    private Set<ProfileBusiness> profileBusiness;

    @JsonIgnore
    @OneToMany(mappedBy = "identifyBusinessType")
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;

    public TypeOfBusiness(String typeName) {
        this.typeName = typeName;
    }
}

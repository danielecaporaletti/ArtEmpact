package com.artempact.backend.artempact.entity.profile.business;

import com.artempact.backend.artempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.artempact.entity.lookup.TypeOfBusiness;
import com.artempact.backend.artempact.entity.lookup.WorkPreference;
import com.artempact.backend.artempact.entity.profile.Profile;
import com.artempact.backend.artempact.entity.profile.business.junction.BusinessCityTarget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "artempact", name = "ProfileBusiness")
public class ProfileBusiness extends Profile {

    @Column(name = "businessName", nullable = false, length = 100)
    private String businessName;

    //`@ManyToOne` indica che molti `ProfileBusiness` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfBusiness_id", referencedColumnName = "id")
    private TypeOfBusiness identifyBusinessType;

    // `@OneToMany` indica che un singolo `ProfileBusiness` può essere associato a molte `CityTarget`
    @OneToMany(mappedBy = "profileBusiness", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BusinessCityTarget> businessCityTargets;

    // `@OneToMany` indica che un singolo `ProfileBusiness` può essere associato a molti `BusinessSeeksCreative`
    @OneToMany(mappedBy = "profileBusiness", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BusinessSeeksCreative> businessSeeksCreatives;
}

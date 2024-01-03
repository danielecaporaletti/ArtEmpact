package com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.entity.card.businessSeeksCreative.BusinessSeeksCreative;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import com.artempact.backend.mysqlArtempact.entity.profile.Profile;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.CreativeSearchLocations;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "ProfileBusiness")
public class ProfileBusiness extends Profile {

    @NotBlank(message = "The businessName cannot be empty")
    @Size(max = 100, message = "The businessName exceeds 100 characters")
    @Column(name = "businessName", nullable = false, length = 100)
    private String businessName;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfBusiness_id", referencedColumnName = "id")
    private TypeOfBusiness typeOfBusiness;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `WorkPreference`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workPreference_id", referencedColumnName = "id")
    private WorkPreference workPreference;
    /*
    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `Photo`
    @OneToMany(mappedBy = "profileBusiness", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhotoBusiness> photoBusinesses;
     */
    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `JobSearchLocation`
    @OneToMany(mappedBy = "profileBusiness", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CreativeSearchLocations> creativeSearchLocations;

    // `@OneToMany` indica che un singolo `ProfileBusiness` può essere associato a molti `BusinessSeeksCreative`
    @OneToMany(mappedBy = "profileBusiness", fetch = FetchType.EAGER)
    private Set<BusinessSeeksCreative> businessSeeksCreatives;

    // Costruttore con campi obbligatori
    public ProfileBusiness(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province, String businessName) {
        super(id, email, phone, name, surname, dob, city, province);
        this.businessName = businessName;
    }
}

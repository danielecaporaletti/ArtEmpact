package com.artempact.backend.mysqlArtempact.entity.profile.profileCreative;

import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfCreative;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import com.artempact.backend.mysqlArtempact.entity.profile.Profile;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.junctionTables.JobSearchLocation;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(schema = "artempact", name = "ProfileCreative")
public class ProfileCreative extends Profile {

    @NotBlank(message = "The creativeName cannot be empty")
    @Size(max = 100, message = "The creativeName exceeds 100 characters")
    @Column(name = "creativeName", nullable = false, length = 100)
    private String creativeName;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `TypeOfCreative`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfCreative_id", referencedColumnName = "id")
    private TypeOfCreative typeOfCreative;

    @Size(max = 100, message = "The sectorOfCompetence exceeds 100 characters")
    @Column(name = "sectorOfCompetence", length = 100)
    private String sectorOfCompetence;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `EducationType`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "educationType_id", referencedColumnName = "id")
    private EducationType educationType;

    @Min(value = 1000, message = "The year is not valid")
    @Max(value = 9000,message = "The year is not valid")
    @Column(name = "finalYearOfEducation", columnDefinition = "SMALLINT")
    private Short finalYearOfEducation;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `WorkPreference`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workPreference_id", referencedColumnName = "id")
    private WorkPreference workPreference;

    /*
    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `Photo`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhotoCreative> photoCreatives;
     */

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `JobSearchLocation`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobSearchLocation> jobSearchLocations;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molti `ProjectCreative`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER)
    private Set<ProjectCreative> projectCreatives;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molti `CreativeSeeksBusiness`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER)
    private Set<CreativeSeeksBusiness> creativeSeeksBusinesses;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molti `CreativeSeeksCollaboration`
    @OneToMany(mappedBy = "profileCreative", fetch = FetchType.EAGER)
    private Set<CreativeSeeksCollaboration> creativeSeeksCollaborations;

    public ProfileCreative(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province, String creativeName) {
        super(id, email, phone, name, surname, dob, city, province);
        this.creativeName = creativeName;
    }
}

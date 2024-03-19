package com.artempact.backend.artempact.entity.profile;

import com.artempact.backend.artempact.entity.LocalityInterface;
import com.artempact.backend.artempact.entity.lookup.WorkPreference;
import com.artempact.backend.artempact.validator.Adult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Profile {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class Locality implements LocalityInterface {
        @Column(name = "city", nullable = false, length = 100)
        private String city;
        @Column(name = "province", nullable = false, length = 2)
        private String province;
        @JsonIgnore
        @Column(name = "lat")
        private Double lat;
        @JsonIgnore
        @Column(name = "lon")
        private Double lon;
    }

    @JsonIgnore
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private String id;
    @Column(name = "email", nullable = false, length = 254, unique = true)
    private String email;
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;
    @Adult
    @Column(name = "dob", nullable = false)
    private LocalDate dob;
    @Embedded
    private Profile.Locality locality;
    @Column(name = "description", length = 280)
    private String description;
    @Column(name = "maxDistance", columnDefinition = "SMALLINT")
    private Short maxDistance;
    @Column(name = "profileCompletionPercentage", columnDefinition = "SMALLINT")
    private Short profileCompletionPercentage;

    //`@ManyToOne` indica che molti `Profile` possono essere associati a un singolo `WorkPreference`
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workPreference_id", referencedColumnName = "id")
    private WorkPreference identifyWorkPreference;

    // Files
    @Column(name = "photo1", length = 280)
    private String photo1;
    @Column(name = "photo2", length = 280)
    private String photo2;
    @Column(name = "photo3", length = 280)
    private String photo3;
    @Column(name = "photoPremium1", length = 280)
    private String photoPremium1;
    @Column(name = "photoPremium2", length = 280)
    private String photoPremium2;
    @Column(name = "document1", length = 280)
    private String document1;
}

package com.artempact.backend.mysqlArtempact.entity.profile;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    public static class Locality implements LocationInterface{
        @NotBlank(message = "The city cannot be empty")
        @Size(max = 100, message = "The city exceeds 100 characters")
        @Column(name = "city", nullable = false, length = 100)
        private String city;

        @NotBlank(message = "The province cannot be empty")
        @Size(max = 2, message = "The province exceeds 2 characters")
        @Column(name = "province", nullable = false, length = 2)
        private String province;

        @JsonIgnore
        @NotNull(message = "The latitude cannot be empty")
        @Column(name = "lat", nullable = false)
        private Double lat;

        @JsonIgnore
        @NotNull(message = "The longitude cannot be empty")
        @Column(name = "lon", nullable = false)
        private Double lon;
    }

    // This ID come from keycloak database
    @JsonIgnore
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private String id;

    @NotBlank( message = "The email cannot be empty")
    @Size(max = 254, message = "The email exceeds 254 characters")
    @Column(name = "email", nullable = false, length = 254, unique = true)
    private String email;

    @NotBlank(message = "The phone cannot be empty")
    @Size(max = 20, message = "The phone exceeds 20 numbers")
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @NotBlank(message = "The name cannot be empty")
    @Size(max = 50, message = "The name exceeds 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "The surname cannot be empty")
    @Size(max = 50, message = "The surname exceeds 50 characters")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "The date of birth cannot be empty")
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Embedded
    private ProfileCreative.Locality locality;

    @Size(max = 280, message = "The description exceeds 280 characters")
    @Column(name = "description", length = 280)
    private String description;

    @Min(value = 0, message = "The distance have to be positive")
    @Max(value = 10000,message = "The max distance is 10000")
    @Column(name = "maxDistance", columnDefinition = "SMALLINT")
    private Short maxDistance;

    @Min(value = 0, message = "The profileCompletionPercentage have to be positive")
    @Max(value = 100,message = "The max profileCompletionPercentage is 100")
    @Column(name = "profileCompletionPercentage", columnDefinition = "SMALLINT")
    private Short profileCompletionPercentage;

    // Photo Names
    @Size(max = 280, message = "The photo name exceeds 280 characters")
    @Column(name = "photo1", length = 280)
    private String photo1;

    @Size(max = 280, message = "The photo name exceeds 280 characters")
    @Column(name = "photo2", length = 280)
    private String photo2;

    @Size(max = 280, message = "The photo name exceeds 280 characters")
    @Column(name = "photo3", length = 280)
    private String photo3;

    @Size(max = 280, message = "The photo name exceeds 280 characters")
    @Column(name = "photoPremium1", length = 280)
    private String photoPremium1;

    @Size(max = 280, message = "The photo name exceeds 280 characters")
    @Column(name = "photoPremiumL2", length = 280)
    private String photoPremiumL2;

    // Document
    @Size(max = 280, message = "The document name exceeds 280 characters")
    @Column(name = "document1", length = 280)
    private String document1;

    public Profile(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.locality = new Locality(city, province, null, null);
    }
}

package com.artempact.backend.mysqlArtempact.dto.profile;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class ProfileDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class Locality {
        @NotBlank(message = "The city cannot be empty")
        @Size(max = 100, message = "The city exceeds 100 characters")
        private String city;

        @NotBlank(message = "The province cannot be empty")
        @Size(max = 2, message = "The province exceeds 2 characters")
        private String province;
    }

    @Size(max = 254, message = "The email exceeds 254 characters")
    private String email;

    @Size(max = 20, message = "The phone exceeds 20 numbers")
    private String phone;

    @Size(max = 50, message = "The name exceeds 50 characters")
    private String name;

    @Size(max = 50, message = "The surname exceeds 50 characters")
    private String surname;

    private LocalDate dob;

    @Embedded
    private ProfileDTO.Locality locality;

    @Size(max = 280, message = "The description exceeds 280 characters")
    private String description;

    @Min(value = 0, message = "The distance have to be positive")
    @Max(value = 10000,message = "The max distance is 10000Km")
    private Short maxDistanceKm;

    @Min(value = 0, message = "The profileCompletionPercentage have to be positive")
    @Max(value = 100,message = "The max profileCompletionPercentage is 100")
    private Short profileCompletionPercentage;
}

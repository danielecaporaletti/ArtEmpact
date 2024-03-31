package com.artempact.backend.artempact.dto;

import com.artempact.backend.artempact.validator.Adult;
import com.artempact.backend.artempact.validator.ValidId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class ProfileDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class Locality {
        @NotBlank(message = "The city cannot be empty")
        @Size(max = 100, message = "The city exceed 100 characters")
        private String city;
        @NotBlank(message = "The province cannot be empty")
        @Size(max = 2, message = "The province exceed 2 characters")
        private String province;
    }

    @Email
    private String email;
    @Size(max = 20, message = "The phone exceed 254 characters")
    private String phone;
    @Size(max = 50, message = "The name exceed 50 characters")
    private String name;
    @Size(max = 50, message = "The surname exceed 50 characters")
    private String surname;
    @Adult
    private LocalDate dob;
    @Embedded
    private ProfileDTO.Locality locality;
    @Size(max = 280, message = "The description exceed 280 characters")
    private String description;
    private Short maxDistance;
    @Min(value = 0, message = "The profileCompletionPercentage must be bigger than or equal to 0")
    @Max(value = 100, message = "The profileCompletionPercentage cannot exceed 100")
    private Short profileCompletionPercentage;
    @ValidId(value = "workPreference")
    private Short identifyWorkPreference;
}

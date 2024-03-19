package com.artempact.backend.artempact.dto;

import com.artempact.backend.artempact.validator.Adult;
import com.artempact.backend.artempact.validator.ValidId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "The email cannot be empty")
    @Size(max = 254, message = "The email exceed 254 characters")
    private String email;
    @NotBlank(message = "The phone cannot be empty")
    @Size(max = 20, message = "The phone exceed 254 characters")
    private String phone;
    @NotBlank(message = "The name cannot be empty")
    @Size(max = 50, message = "The name exceed 50 characters")
    private String name;
    @NotBlank(message = "The surname cannot be empty")
    @Size(max = 50, message = "The surname exceed 50 characters")
    private String surname;
    @Adult
    private LocalDate dob;
    @Embedded
    private ProfileDTO.Locality locality;
    @NotBlank(message = "The description cannot be empty")
    @Size(max = 280, message = "The description exceed 280 characters")
    private String description;
    private Short maxDistance;
    @NotBlank(message = "The profileCompletionPercentage cannot be empty")
    @Size(min = 0, message = "The profileCompletionPercentage must be bigger than 0")
    @Size(max = 100, message = "The profileCompletionPercentage exceed value 100")
    private Short profileCompletionPercentage;
    @ValidId(value = "workPreference")
    private Short identifyWorkPreference;
}

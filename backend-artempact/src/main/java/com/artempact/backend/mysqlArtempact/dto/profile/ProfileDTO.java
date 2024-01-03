package com.artempact.backend.mysqlArtempact.dto.profile;

import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class ProfileDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class Locality implements LocationInterface {
        private String city;
        private String province;
        private Double lat;
        private Double lon;
    }

    private String email;
    private String phone;
    private String name;
    private String surname;
    private String dob;
    @Embedded
    private ProfileDTO.Locality locality;
    private String description;
    private String maxDistance;
    private String profileCompletionPercentage;
}

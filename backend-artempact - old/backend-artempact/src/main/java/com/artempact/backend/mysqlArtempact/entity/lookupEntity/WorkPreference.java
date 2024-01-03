package com.artempact.backend.mysqlArtempact.entity.lookupEntity;

import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "artempact", name = "WorkPreference")
public class WorkPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SMALLINT", updatable = false, nullable = false, unique = true)
    private Short id;

    @NotBlank(message = "The WorkPreference cannot be empty")
    @Size(max = 100, message = "The WorkPreference exceeds 100 characters")
    @Column(name = "workPreferenceName ", nullable = false, length = 100)
    private String workPreferenceName ;

    public WorkPreference(String workPreferenceName ) {
        this.workPreferenceName = workPreferenceName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "workPreference")
    private Set<ProfileBusiness> profileBusiness;

    @JsonIgnore
    @OneToMany(mappedBy = "workPreference")
    private Set<ProfileCreative> profileCreatives;
}

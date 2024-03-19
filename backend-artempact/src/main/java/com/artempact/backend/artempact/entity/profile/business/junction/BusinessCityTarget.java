package com.artempact.backend.artempact.entity.profile.business.junction;

import com.artempact.backend.artempact.entity.LocalityInterface;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "artempact", name = "BusinessCityTarget")
public class BusinessCityTarget implements LocalityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

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

    // `@ManyToOne` indica che molte `CityTarget` possono essere associate a un singolo `ProfileBusiness`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_business_id")
    private ProfileBusiness profileBusiness;
}

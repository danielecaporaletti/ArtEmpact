package com.artempact.backend.artempact.entity.profile.creative.junction;

import com.artempact.backend.artempact.entity.LocalityInterface;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "artempact", name = "CreativeCityTarget")
public class CreativeCityTarget implements LocalityInterface {

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

    // `@ManyToOne` indica che molte `JobSearchLocation` possono essere associate a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;
}

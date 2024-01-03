package com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.junctionTable;

import com.artempact.backend.mysqlArtempact.entity.card.creativeSeeksBusiness.CreativeSeeksBusiness;
import com.artempact.backend.mysqlArtempact.entity.profile.LocationInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "artempact", name = "JobSearchLocationCard")
public class JobSearchLocationCard implements LocationInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

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

    // `@ManyToOne` indica che molte `JobSearchLocationCard` possono essere associate a un singolo `CreativeSeeksBusiness`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creative_seek_business_id")
    private CreativeSeeksBusiness creativeSeeksBusiness;
}

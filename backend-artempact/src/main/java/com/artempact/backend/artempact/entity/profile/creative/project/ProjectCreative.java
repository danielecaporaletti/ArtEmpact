package com.artempact.backend.artempact.entity.profile.creative.project;

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
@Table(schema = "artempact", name = "ProjectCreative")
public class ProjectCreative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "year", columnDefinition = "SMALLINT")
    private Short year;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @Column(name = "description", nullable = false, length = 280)
    private String description;

    @Column(name = "customer", length = 50)
    private String customer;

    @Column(name = "link", length = 1000)
    private String link;

    // `@ManyToOne` indica che molti `ProjectCreative` possono essere associati a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;

    // Files
    @Column(name = "photoProject1", length = 280)
    private String photoProject1;
    @Column(name = "photoProject2", length = 280)
    private String photoProject2;
    @Column(name = "photoProject3", length = 280)
    private String photoProject3;
}

package com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.junctionTables.PhotoProjectCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "ProjectCreative")
public class ProjectCreative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Size(max = 50, message = "The name exceeds 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // `@OneToMany` indica che un singolo `ProjectCreative` può essere associato a molte `PhotoProjectCreative`
    @OneToMany(mappedBy = "projectCreative", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhotoProjectCreative> photoProjectCreatives;

    @Min(value = 1000, message = "The year is not valid")
    @Max(value = 9000,message = "The year is not valid")
    @Column(name = "year", columnDefinition = "SMALLINT")
    private Short year;

    @NotBlank(message = "The type cannot be empty")
    @Size(max = 100, message = "The type exceeds 100 characters")
    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @NotBlank(message = "The description cannot be empty")
    @Size(max = 280, message = "The description exceeds 280 characters")
    @Column(name = "description", nullable = false, length = 280)
    private String description;

    @Size(max = 50, message = "The customer exceeds 50 characters")
    @Column(name = "customer", length = 50)
    private String customer;

    @Size(max = 1000, message = "The link exceeds 1000 characters")
    @Column(name = "link", length = 1000)
    private String link;

    // Constructor with not nullable values
    public ProjectCreative(String name, Short year, String type, String description) {
        this.name = name;
        this.year = year;
        this.type = type;
        this.description = description;
    }

    // `@ManyToOne` indica che molti `ProjectCreative` possono essere associati a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;
}

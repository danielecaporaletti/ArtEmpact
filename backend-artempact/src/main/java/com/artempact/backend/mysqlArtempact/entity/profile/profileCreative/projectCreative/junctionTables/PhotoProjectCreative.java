package com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.junctionTables;
/*
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "PhotoProjectCreative")
public class PhotoProjectCreative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "The photoLink cannot be empty")
    @Size(max = 1000, message = "The photoLink exceeds 1000 characters")
    @Column(name = "photoLink", nullable = false, length = 1000)
    private String photoLink;

    public PhotoProjectCreative(String photoLink ) {
        this.photoLink = photoLink;
    }

    // `@ManyToOne` indica che molte `Photo` possono essere associate a un singolo `ProjectCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_creative_id")
    private ProjectCreative projectCreative;
}

 */
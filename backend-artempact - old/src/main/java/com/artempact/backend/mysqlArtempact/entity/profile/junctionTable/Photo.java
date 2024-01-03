package com.artempact.backend.mysqlArtempact.entity.profile.junctionTable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "The photoLink cannot be empty")
    @Size(max = 1000, message = "The photoLink exceeds 1000 characters")
    @Column(name = "photoLink", nullable = false, length = 1000)
    private String photoLink;

    public Photo(String photoLink) {
        this.photoLink = photoLink;
    }
}

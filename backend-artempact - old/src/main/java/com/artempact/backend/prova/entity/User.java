package com.artempact.backend.prova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @JsonIgnore
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @NotBlank( message = "Il nome non può essere vuoto")
    @Size(max = 50, message = "La lunghezza massima del nome è 50")
    private String name;

    @NotNull(message = "L'età non può essere nulla")
    @Min(value = 18, message = "L'età minima è 18")
    private Integer age;
}

package com.artempact.backend.prova.dto;

import com.artempact.backend.prova.config.StringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPatchDTO {

    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(max = 50, message = "La lunghezza massima del nome è 50")
    @JsonDeserialize(using = StringDeserializer.class)
    private String name;

    @Min(value = 18, message = "L'età minima è 18")
    private Integer age;
}

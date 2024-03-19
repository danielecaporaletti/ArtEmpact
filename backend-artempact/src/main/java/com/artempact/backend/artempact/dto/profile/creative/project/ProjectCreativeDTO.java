package com.artempact.backend.artempact.dto.profile.creative.project;

import com.artempact.backend.artempact.validator.ValidYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCreativeDTO {
    @NotBlank(message = "The name cannot be empty")
    @Size(max = 50, message = "The name exceed 50 characters")
    private String name;
    @ValidYear(message = "The year must be between 1990 and now")
    private Short year;
    @NotBlank(message = "The type cannot be empty")
    @Size(max = 100, message = "The type exceed 100 characters")
    private String type;
    @NotBlank(message = "The description cannot be empty")
    @Size(max = 280, message = "The description exceed 280 characters")
    private String description;
    @NotBlank(message = "The customer cannot be empty")
    @Size(max = 50, message = "The customer exceed 50 characters")
    private String customer;
    @NotBlank(message = "The link cannot be empty")
    @Size(max = 1000, message = "The link exceed 1000 characters")
    private String link;
}

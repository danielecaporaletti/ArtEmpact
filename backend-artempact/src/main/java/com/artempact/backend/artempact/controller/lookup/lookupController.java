package com.artempact.backend.artempact.controller.lookup;

import com.artempact.backend.artempact.entity.lookup.*;
import com.artempact.backend.artempact.repository.repository.lookup.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class lookupController {
    private final EducationTypeRepository educationTypeRepository;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private final TypeOfBusinessRepository typeOfBusinessRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final ProfessionalRelationshipRepository professionalRelationshipRepository;

    @Value("${backend.version:unknown version}")
    private String backendVersion;

    @GetMapping("/backendVersion")
    public String getVersionBackend() {
        return backendVersion;
    }

    @GetMapping("/educationType")
    public List<EducationType> getAllEducationTypes() {
        return educationTypeRepository.findAll();
    }

    @GetMapping("/typeOfCreative")
    public List<TypeOfCreative> getAllTypeOfCreatives() {
        return typeOfCreativeRepository.findAll();
    }

    @GetMapping("/typeOfBusiness")
    public List<TypeOfBusiness> getAllTypeOfBusiness() {
        return typeOfBusinessRepository.findAll();
    }

    @GetMapping("/workPreference")
    public List<WorkPreference> getAllWorkPreferences() {
        return workPreferenceRepository.findAll();
    }

    @GetMapping("/experienceLevel")
    public List<ExperienceLevel> getAllExperienceLevels() {
        return experienceLevelRepository.findAll();
    }

    @GetMapping("/professionalRelationship")
    public List<ProfessionalRelationship> getAllProfessionalRelationships() {
        return professionalRelationshipRepository.findAll();
    }
}

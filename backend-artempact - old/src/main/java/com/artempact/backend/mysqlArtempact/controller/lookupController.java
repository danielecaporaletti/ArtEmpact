package com.artempact.backend.mysqlArtempact.controller;

import com.artempact.backend.mysqlArtempact.repository.lookupRepository.*;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.*;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.EducationTypeRepository;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.TypeOfCreativeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class lookupController {

    private final EducationTypeRepository educationTypeRepository;
    private final TypeOfCreativeRepository typeOfCreativeRepository;
    private TypeOfBusinessRepository typeOfBusinessRepository;
    private final WorkPreferenceRepository workPreferenceRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final ProfessionalRelationshipRepository professionalRelationshipRepository;

    public lookupController(EducationTypeRepository educationTypeRepository, TypeOfCreativeRepository typeOfCreativeRepository, TypeOfBusinessRepository typeOfBusinessRepository, WorkPreferenceRepository workPreferenceRepository, ExperienceLevelRepository experienceLevelRepository, ProfessionalRelationshipRepository professionalRelationshipRepository) {
        this.educationTypeRepository = educationTypeRepository;
        this.typeOfCreativeRepository = typeOfCreativeRepository;
        this.typeOfBusinessRepository = typeOfBusinessRepository;
        this.workPreferenceRepository = workPreferenceRepository;
        this.experienceLevelRepository = experienceLevelRepository;
        this.professionalRelationshipRepository = professionalRelationshipRepository;
    }

    @GetMapping("/educationType")
    public List<InlineEducationType> getAllEducationTypes() {
        return educationTypeRepository.findAllProjectedBy();
    }

    @GetMapping("/typeOfCreative")
    public List<InlineTypeOfCreative> getAllTypeOfCreatives() {
        return typeOfCreativeRepository.findAllProjectedBy();
    }

    @GetMapping("/workPreference")
    public List<InlineWorkPreference> getAllWorkPreferences() {
        return workPreferenceRepository.findAllProjectedBy();
    }

    @GetMapping("/typeOfBusiness")
    public List<InlineTypeOfBusiness> getAllTypeOfBusiness() {
        return typeOfBusinessRepository.findAllProjectedBy();
    }

    @GetMapping("/experienceLevel")
    public List<InlineExperienceLevel> getAllTypeOfExperience() {
        return experienceLevelRepository.findAllProjectedBy();
    }

    @GetMapping("/professionalRelationship")
    public List<InlineProfessionalRelationship> getAllTypeOfRelationship() {
        return professionalRelationshipRepository.findAllProjectedBy();
    }
}

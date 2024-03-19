package com.artempact.backend;

import com.artempact.backend.artempact.entity.lookup.*;
import com.artempact.backend.artempact.repository.repository.lookup.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.artempact.backend.artempact.repository.repository")
public class BackendApplication {

	@Autowired
	private EducationTypeRepository educationTypeRepository;
	@Autowired
	private TypeOfCreativeRepository typeOfCreativeRepository;
	@Autowired
	private TypeOfBusinessRepository typeOfBusinessRepository;
	@Autowired
	private WorkPreferenceRepository workPreferenceRepository;
	@Autowired
	private ExperienceLevelRepository experienceLevelRepository;
	@Autowired
	private ProfessionalRelationshipRepository professionalRelationshipRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	public void initData() {
		// Inizializza EducationType
		if (educationTypeRepository.count() == 0) {
			educationTypeRepository.saveAll(Arrays.asList(
					new EducationType("High School Diploma"),
					new EducationType("Bachelor's Degree"),
					new EducationType("Master's Degree"),
					new EducationType("Postgraduate Master's Degree")
			));
		}

		// Inizializza TypeOfCreative
		if (typeOfCreativeRepository.count() == 0) {
			typeOfCreativeRepository.saveAll(Arrays.asList(
					new TypeOfCreative("Visual Artist"),
					new TypeOfCreative("Street Art"),
					new TypeOfCreative("Digital Art"),
					new TypeOfCreative("Writer"),
					new TypeOfCreative("Musician")
			));
		}

		// Inizializza TypeOfBusiness
		if (typeOfBusinessRepository.count() == 0) {
			typeOfBusinessRepository.saveAll(Arrays.asList(
					new TypeOfBusiness("Publicly Traded Company"),
					new TypeOfBusiness("Self-Employed Worker"),
					new TypeOfBusiness("Government Entity"),
					new TypeOfBusiness("Non-Profit Organization"),
					new TypeOfBusiness("Sole Proprietorship"),
					new TypeOfBusiness("Private Company (Non-Public)"),
					new TypeOfBusiness("Partnership")
			));
		}

		// Inizializza WorkPreference
		if (workPreferenceRepository.count() == 0) {
			workPreferenceRepository.saveAll(Arrays.asList(
					new WorkPreference("Remote Work"),
					new WorkPreference("Hybrid Work"),
					new WorkPreference("On-Site Work")
			));
		}

		// Inizializza ExperienceLevel
		if (experienceLevelRepository.count() == 0) {
			experienceLevelRepository.saveAll(Arrays.asList(
					new ExperienceLevel("Entry Level"),
					new ExperienceLevel("1-5 Years"),
					new ExperienceLevel("5-10 Years"),
					new ExperienceLevel("Over 10 Years")
			));
		}

		// Inizializza ProfessionalRelationship
		if (professionalRelationshipRepository.count() == 0) {
			professionalRelationshipRepository.saveAll(Arrays.asList(
					new ProfessionalRelationship("Full Time"),
					new ProfessionalRelationship("Part Time"),
					new ProfessionalRelationship("Contract"),
					new ProfessionalRelationship("Internship"),
					new ProfessionalRelationship("Freelance")
			));
		}

	}


}

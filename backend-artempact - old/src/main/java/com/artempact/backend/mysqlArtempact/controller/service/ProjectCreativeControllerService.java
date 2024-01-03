package com.artempact.backend.mysqlArtempact.controller.service;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.junctionTables.PhotoProjectCreative;
import com.artempact.backend.mysqlArtempact.repository.profileCreative.projectCreative.junctionTables.PhotoProjectCreativeRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Set;

@Service
public class ProjectCreativeControllerService {

    private final PhotoProjectCreativeRepository photoProjectCreativeRepository;

    public ProjectCreativeControllerService(PhotoProjectCreativeRepository photoProjectCreativeRepository) {
        this.photoProjectCreativeRepository = photoProjectCreativeRepository;
    }

    public void handlePhotoProjectCreativesUpdate(ProjectCreative project, List<String> photoLinks, Errors errors) {
        Set<PhotoProjectCreative> currentPhotoLinks = project.getPhotoProjectCreatives();

        // Trova i photo link che non sono presenti nella nuova lista e li rimuove
        currentPhotoLinks.removeIf(photo -> !photoLinks.contains(photo.getPhotoLink()));

        // Identifica o crea le nuove foto da aggiungere
        for (String photoLink : photoLinks) {
            try {
                currentPhotoLinks.stream()
                        .filter(photo -> photo.getPhotoLink().equals(photoLink))
                        .findFirst()
                        .orElseGet(() -> {
                            PhotoProjectCreative newPhotoLink = new PhotoProjectCreative(photoLink);
                            newPhotoLink.setProjectCreative(project);
                            photoProjectCreativeRepository.save(newPhotoLink); // Salva immediatamente la nuova photoLink
                            return newPhotoLink;
                        });
            } catch (ValidationException e) {
                errors.rejectValue("photoProjectCreatives", "InvalidType", "Invalid type for photoLink: " + e.getMessage());
                // You might want to break or continue based on your error handling strategy
            }
        }
    }
}


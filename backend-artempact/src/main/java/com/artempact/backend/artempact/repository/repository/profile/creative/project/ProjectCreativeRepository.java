package com.artempact.backend.artempact.repository.repository.profile.creative.project;

import com.artempact.backend.artempact.entity.profile.creative.project.ProjectCreative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCreativeRepository extends JpaRepository<ProjectCreative, Long> {
}

package com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.projectCreative.ProjectCreative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCreativeRepository extends JpaRepository<ProjectCreative, Long> {
}

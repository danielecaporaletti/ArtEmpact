package com.artempact.backend.mysqlArtempact.repository.lookupRepository;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ExperienceLevel;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.InlineExperienceLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceLevelRepository extends JpaRepository<ExperienceLevel, Short> {
    @Query("SELECT e.id FROM ExperienceLevel e")
    List<Short> findAllIds();

    @Query("SELECT e FROM ExperienceLevel e")
    List<InlineExperienceLevel> findAllProjectedBy();
}

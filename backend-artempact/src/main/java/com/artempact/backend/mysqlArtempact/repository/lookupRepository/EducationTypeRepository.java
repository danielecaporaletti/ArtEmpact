package com.artempact.backend.mysqlArtempact.repository.lookupRepository;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.EducationType;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.InlineEducationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationTypeRepository extends JpaRepository<EducationType, Short> {
    @Query("SELECT e.id FROM EducationType e")
    List<Short> findAllIds();

    @Query("SELECT e FROM EducationType e")
    List<InlineEducationType> findAllProjectedBy();
}

package com.artempact.backend.mysqlArtempact.repository.lookupRepository;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.WorkPreference;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.InlineWorkPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPreferenceRepository extends JpaRepository<WorkPreference, Short> {
    // Metodo per ottenere tutti gli ID
    @Query("SELECT w.id FROM WorkPreference w")
    List<Short> findAllIds();

    @Query("SELECT w FROM WorkPreference w")
    List<InlineWorkPreference> findAllProjectedBy();
}


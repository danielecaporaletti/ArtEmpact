package com.artempact.backend.mysqlArtempact.repository.lookupRepository;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.ProfessionalRelationship;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.InlineProfessionalRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRelationshipRepository extends JpaRepository<ProfessionalRelationship, Short> {

    @Query("SELECT r.id FROM ProfessionalRelationship r")
    List<Short> findAllIds();

    @Query("SELECT r FROM ProfessionalRelationship r")
    List<InlineProfessionalRelationship> findAllProjectedBy();
}

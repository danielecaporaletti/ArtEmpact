package com.artempact.backend.mysqlArtempact.repository.profileBusiness.junctionTables;

import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.CreativeSearchLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreativeSearchLocationRepository extends JpaRepository<CreativeSearchLocations, Long> {
}

package com.artempact.backend.mysqlArtempact.repository.profileCreative.junctionTables;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.junctionTables.JobSearchLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSearchLocationRepository extends JpaRepository<JobSearchLocation, Long> {
}

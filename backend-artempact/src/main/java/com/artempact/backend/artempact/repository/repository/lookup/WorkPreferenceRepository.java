package com.artempact.backend.artempact.repository.repository.lookup;

import com.artempact.backend.artempact.entity.lookup.WorkPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPreferenceRepository extends JpaRepository<WorkPreference, Short> {

}

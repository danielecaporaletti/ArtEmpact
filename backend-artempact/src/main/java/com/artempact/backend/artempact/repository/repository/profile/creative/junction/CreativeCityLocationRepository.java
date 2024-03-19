package com.artempact.backend.artempact.repository.repository.profile.creative.junction;

import com.artempact.backend.artempact.entity.profile.creative.junction.CreativeCityTarget;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericLocationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreativeCityLocationRepository extends GenericLocationRepository<CreativeCityTarget, Long> {

}

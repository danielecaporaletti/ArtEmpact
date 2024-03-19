package com.artempact.backend.artempact.repository.repository.profile.business.junction;

import com.artempact.backend.artempact.entity.profile.business.junction.BusinessCityTarget;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericLocationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCityLocationRepository extends GenericLocationRepository<BusinessCityTarget, Long> {
}

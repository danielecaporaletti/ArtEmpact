package com.artempact.backend.artempact.repository.repository.profile.business;

import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileBusinessRepository extends GenericProfileRepository<ProfileBusiness, String> {
}

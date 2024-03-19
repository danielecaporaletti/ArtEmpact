package com.artempact.backend.artempact.repository.repository.profile.creative;

import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileCreativeRepository extends GenericProfileRepository<ProfileCreative, String> {
}

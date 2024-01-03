package com.artempact.backend.mysqlArtempact.repository.profileCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileCreativeRepository extends JpaRepository<ProfileCreative, String> {
}

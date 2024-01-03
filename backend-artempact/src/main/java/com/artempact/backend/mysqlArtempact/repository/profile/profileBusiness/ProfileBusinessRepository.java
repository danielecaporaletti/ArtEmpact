package com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.entity.profile.Profile;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileBusinessRepository extends JpaRepository<ProfileBusiness, String> {
}

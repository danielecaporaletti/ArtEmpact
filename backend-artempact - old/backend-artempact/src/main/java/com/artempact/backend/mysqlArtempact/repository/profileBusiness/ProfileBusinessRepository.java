package com.artempact.backend.mysqlArtempact.repository.profileBusiness;

import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileBusinessRepository extends JpaRepository<ProfileBusiness, String> {
}

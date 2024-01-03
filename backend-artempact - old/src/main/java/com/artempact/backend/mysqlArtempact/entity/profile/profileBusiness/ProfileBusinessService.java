package com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.entity.profile.ProfileService;
import com.artempact.backend.mysqlArtempact.repository.profileBusiness.ProfileBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProfileBusinessService extends ProfileService<ProfileBusiness> {

    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;

    @Override
    protected ProfileBusiness createProfileInstance(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province, String specificName) {
        return new ProfileBusiness(id, email, phone, name, surname, dob, city, province, specificName);
    }

    @Override
    protected JpaRepository<ProfileBusiness, String> getRepository() {
        return profileBusinessRepository;
    }
}

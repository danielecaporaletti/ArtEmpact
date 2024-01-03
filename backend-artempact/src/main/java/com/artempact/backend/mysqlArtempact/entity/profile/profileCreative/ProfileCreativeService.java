package com.artempact.backend.mysqlArtempact.entity.profile.profileCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.ProfileService;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProfileCreativeService extends ProfileService<ProfileCreative> {

    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;

    @Override
    protected ProfileCreative createProfileInstance(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province, String specificName) {
        return new ProfileCreative(id, email, phone, name, surname, dob, city, province, specificName);
    }

    @Override
    protected JpaRepository<ProfileCreative, String> getRepository() {
        return profileCreativeRepository;
    }
}



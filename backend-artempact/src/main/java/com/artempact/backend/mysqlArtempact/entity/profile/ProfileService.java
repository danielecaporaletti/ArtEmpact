package com.artempact.backend.mysqlArtempact.entity.profile;

import com.artempact.backend.mysqlGeographic.controller.service.GeographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class ProfileService<T extends Profile> {

    @Autowired
    private GeographicService geographicService;

    protected abstract T createProfileInstance(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province, String specificName);

    protected abstract JpaRepository<T, String> getRepository();

    public T createProfile(String id, String email, String phone, String name, String surname, LocalDate dob, String city, String province, String specificName) {
        T profile = createProfileInstance(id, email, phone, name, surname, dob, city, province, specificName);

        Optional<List<BigDecimal>> optionalGeo = geographicService.getLatAndLonFromCityAndProvince(city, province);
        if (!optionalGeo.isPresent()) {
            throw new IllegalStateException("Geographic information not found.");
        }

        List<BigDecimal> geo = optionalGeo.get();
        profile.getLocality().setLat(geo.get(0).doubleValue());
        profile.getLocality().setLon(geo.get(1).doubleValue());

        return getRepository().save(profile);
    }
}

package com.artempact.geographical.microserviceGeo.repository;

import com.artempact.geographical.microserviceGeo.entityITA.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<Comune, String> {
    List<Comune> findByDenominazioneItaStartingWithIgnoreCase(String denominazioneIta);

    Optional<Comune> findByDenominazioneItaAndSiglaProvincia(String denominazioneIta, String siglaProvincia);


}



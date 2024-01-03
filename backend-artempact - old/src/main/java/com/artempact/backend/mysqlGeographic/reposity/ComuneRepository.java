package com.artempact.backend.mysqlGeographic.reposity;

import com.artempact.backend.mysqlGeographic.entity.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, String> {
    @Query("SELECT c FROM Comune c WHERE LOWER(c.denominazioneIta) LIKE LOWER(CONCAT(?1, '%'))")
    List<Comune> findComuneByDenominazioneItaStartingWith(String prefix);

    @Query("SELECT c.lat, c.lon FROM Comune c WHERE LOWER(c.denominazioneIta) = LOWER(:citta) AND LOWER(c.siglaProvincia) = LOWER(:provincia)")
    List<Object[]> findLatLonByCittaAndProvincia(@Param("citta") String citta, @Param("provincia") String provincia);
}



package com.artempact.backend.mysqlGeographic.reposity;

import com.artempact.backend.mysqlGeographic.entity.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, String> {
    @Query("SELECT DISTINCT c.denominazioneIta FROM Comune c WHERE LOWER(c.denominazioneIta) LIKE LOWER(CONCAT(?1, '%'))")
    List<String> findDenominazioneItaByStartingWith(String prefix);
}


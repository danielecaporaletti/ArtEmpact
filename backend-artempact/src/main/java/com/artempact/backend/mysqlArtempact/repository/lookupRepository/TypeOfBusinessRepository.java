package com.artempact.backend.mysqlArtempact.repository.lookupRepository;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.InlineTypeOfBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfBusinessRepository extends JpaRepository<TypeOfBusiness, Short> {
    // Metodo per ottenere tutti gli ID
    @Query("SELECT t.id FROM TypeOfBusiness t")
    List<Short> findAllIds();

    @Query("SELECT t FROM TypeOfBusiness t")
    List<InlineTypeOfBusiness> findAllProjectedBy();
}

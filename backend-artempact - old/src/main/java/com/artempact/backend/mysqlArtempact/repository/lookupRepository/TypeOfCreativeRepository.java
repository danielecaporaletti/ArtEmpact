package com.artempact.backend.mysqlArtempact.repository.lookupRepository;

import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfCreative;
import com.artempact.backend.mysqlArtempact.repository.lookupRepository.inline.InlineTypeOfCreative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfCreativeRepository extends JpaRepository<TypeOfCreative, Short> {
    // Metodo per ottenere tutti gli ID
    @Query("SELECT t.id FROM TypeOfCreative t")
    List<Short> findAllIds();

    @Query("SELECT t FROM TypeOfCreative t")
    List<InlineTypeOfCreative> findAllProjectedBy();
}

package com.artempact.backend.artempact.repository.repository;

import com.artempact.backend.artempact.algorithm.entity.CompatibilityCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompatibilityCardRepository extends JpaRepository<CompatibilityCard, Long> {
    Optional<CompatibilityCard> findByFromCardIdAndToCardId(Long fromCardId, Long toCardId);

    List<CompatibilityCard> findByFromCardId(Long fromCardId);
    List<CompatibilityCard> findByToCardId(Long toCardId);
}

package com.artempact.backend.artempact.repository.genericRepositoryNoJPA;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GenericCardRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}

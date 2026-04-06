package com.senai.backend.repository;

import com.senai.backend.model.Freezer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FreezerRepository extends JpaRepository<Freezer, Long> {
    Optional<Freezer> findByNome(String nome);
}
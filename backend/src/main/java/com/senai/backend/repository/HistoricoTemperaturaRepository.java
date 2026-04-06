package com.senai.backend.repository;

import com.senai.backend.model.HistoricoTemperatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoTemperaturaRepository extends JpaRepository<HistoricoTemperatura, Long> {
}

package com.senai.backend.repository;

import com.senai.backend.model.SensorTemperatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SensorTemperaturaRepository extends JpaRepository<SensorTemperatura, Long> {
    Optional<SensorTemperatura> findByNome(String nome);
}

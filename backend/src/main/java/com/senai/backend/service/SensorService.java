package com.senai.backend.service;

import com.senai.backend.model.SensorTemperatura;
import com.senai.backend.repository.SensorTemperaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorTemperaturaRepository sensorRepository;

    public List<SensorTemperatura> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<SensorTemperatura> findById(Long id) {
        return sensorRepository.findById(id);
    }

    public SensorTemperatura save(SensorTemperatura sensor) {
        return sensorRepository.save(sensor);
    }

    public SensorTemperatura update(Long id, SensorTemperatura sensorAtualizado) {
        Optional<SensorTemperatura> sensorExistente = sensorRepository.findById(id);

        if (sensorExistente.isPresent()) {
            SensorTemperatura sensor = sensorExistente.get();
            sensor.setNome(sensorAtualizado.getNome());
            sensor.setFreezer(sensorAtualizado.getFreezer());
            return sensorRepository.save(sensor);
        }
        return null;
    }

    public void delete(Long id) {
        sensorRepository.deleteById(id);
    }
}


package com.senai.backend.service;

import com.senai.backend.model.Sensor;
import com.senai.backend.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findById(Long id) {
        return sensorRepository.findById(id);
    }

    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Sensor update(Long id, Sensor sensorAtualizado) {
        Optional<Sensor> sensorExistente = sensorRepository.findById(id);

        if (sensorExistente.isPresent()) {
            Sensor sensor = sensorExistente.get();
            sensor.setTemperatura(sensorAtualizado.getTemperatura());
            sensor.setUmidade(sensorAtualizado.getUmidade());
            sensor.setDataHora(sensorAtualizado.getDataHora());
            return sensorRepository.save(sensor);
        }
        return null;
    }

    public void delete(Long id) {
        sensorRepository.deleteById(id);
    }
}

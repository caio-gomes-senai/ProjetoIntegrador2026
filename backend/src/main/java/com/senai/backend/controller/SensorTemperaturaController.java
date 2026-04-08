package com.senai.backend.controller;

import com.senai.backend.model.SensorTemperatura;
import com.senai.backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-sensores")
@CrossOrigin(origins = "*")
public class SensorTemperaturaController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorTemperatura>> getAll() {
        return ResponseEntity.ok(sensorService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sensorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

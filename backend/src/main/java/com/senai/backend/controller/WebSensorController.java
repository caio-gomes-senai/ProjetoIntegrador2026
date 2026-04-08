package com.senai.backend.controller;

import com.senai.backend.dto.SensorTemperaturaDTO;
import com.senai.backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sensor")
public class WebSensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/lista")
    public String listSensores(Model model) {
        List<SensorTemperaturaDTO> dtos = sensorService.findAll().stream()
                .map(sensor -> SensorTemperaturaDTO.builder()
                        .id(sensor.getId())
                        .nome("Sensor " + sensor.getId()) // Display name
                        .tipo("DHT11") // Placeholder
                        .status("Ativo") // Placeholder
                        .build())
                .collect(Collectors.toList());
        model.addAttribute("listaSensores", dtos);
        return "sensorLista";
    }
}

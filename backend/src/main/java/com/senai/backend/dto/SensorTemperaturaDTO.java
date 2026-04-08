package com.senai.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorTemperaturaDTO {
    private Long id;
    private String nome;
    private String freezerNome;
    private String status; // For display purposes
    private String tipo;   // For display purposes
}

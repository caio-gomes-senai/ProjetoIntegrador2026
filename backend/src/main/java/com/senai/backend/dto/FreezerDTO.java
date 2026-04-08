package com.senai.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreezerDTO {
    private Long id;
    private String nome;
    private Long sensorId;
    private String sensorNome;
}

package com.senai.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sensores")
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double temperatura;

    @Column(nullable = false)
    private Double umidade;

    @Column(name = "data_hora", nullable = false)
    private String dataHora;
}

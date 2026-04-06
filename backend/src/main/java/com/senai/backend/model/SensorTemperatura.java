package com.senai.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "SENSOR_TEMPERATURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_serial")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_freezer", nullable = false)
    private Freezer freezer;

    @OneToMany(mappedBy = "sensorTemperatura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoricoTemperatura> historicos;
}

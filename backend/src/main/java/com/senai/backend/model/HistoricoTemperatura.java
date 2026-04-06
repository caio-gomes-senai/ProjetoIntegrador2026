package com.senai.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORICO_TEMPERATURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;

    @Column(name = "temperatura", nullable = false)
    private Float temperatura;

    @Column(name = "timestamp", nullable = false, insertable = false, updatable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serial", nullable = false)
    private SensorTemperatura sensorTemperatura;
}

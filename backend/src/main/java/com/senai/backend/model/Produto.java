package com.senai.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PRODUTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "temp_minima", nullable = false)
    private Float tempMinima;

    @Column(name = "temp_maxima", nullable = false)
    private Float tempMaxima;

    @ManyToMany(mappedBy = "produtos", fetch = FetchType.LAZY)
    private List<Freezer> freezers;
}
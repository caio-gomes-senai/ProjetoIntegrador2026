package com.senai.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "FREEZER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Freezer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_freezer")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "freezer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorTemperatura> sensores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "FREEZER_PRODUTO",
        joinColumns = @JoinColumn(name = "id_freezer"),
        inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private List<Produto> produtos;
}
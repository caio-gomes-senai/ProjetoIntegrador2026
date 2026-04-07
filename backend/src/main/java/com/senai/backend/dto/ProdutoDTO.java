package com.senai.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private String nome;
    private LocalDate dataValidade;
    private Float tempMinima;
    private Float tempMaxima;
    private Long freezerId;
}

package com.senai.backend.service;

import com.senai.backend.dto.ProdutoDTO;
import com.senai.backend.model.Freezer;
import com.senai.backend.model.Produto;
import com.senai.backend.repository.FreezerRepository;
import com.senai.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FreezerRepository freezerRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public Produto saveWithFreezer(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDataValidade(dto.getDataValidade());
        produto.setTempMinima(dto.getTemperaturaMinima());
        produto.setTempMaxima(dto.getTemperaturaMaxima());
        
        Produto saved = produtoRepository.save(produto);
        
        if (dto.getFreezerId() != null) {
            Optional<Freezer> freezerOpt = freezerRepository.findById(dto.getFreezerId());
            if (freezerOpt.isPresent()) {
                Freezer freezer = freezerOpt.get();
                if (freezer.getProdutos() == null) {
                    freezer.setProdutos(new ArrayList<>());
                }
                freezer.getProdutos().add(saved);
                freezerRepository.save(freezer);
            }
        }
        return saved;
    }

    public Produto update(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setDataValidade(produtoAtualizado.getDataValidade());
            produto.setTempMinima(produtoAtualizado.getTempMinima());
            produto.setTempMaxima(produtoAtualizado.getTempMaxima());
            return produtoRepository.save(produto);
        }
        return null;
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
package com.senai.backend.controller;

import com.senai.backend.dto.ProdutoDTO;
import com.senai.backend.model.Freezer;
import com.senai.backend.model.Produto;
import com.senai.backend.service.FreezerService;
import com.senai.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produto")
public class WebProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FreezerService freezerService;

    @GetMapping("/lista")
    public String listProdutos(Model model) {
        List<ProdutoDTO> dtos = produtoService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        model.addAttribute("listaProdutos", dtos);
        return "produtolista";
    }

    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("produtoDto", new ProdutoDTO());
        model.addAttribute("listaFreezers", freezerService.findAll());
        return "produtocadastro";
    }

    @PostMapping("/cadastro")
    public String processCadastro(@ModelAttribute("produtoDto") ProdutoDTO produtoDto, Model model) {
        try {
            produtoService.saveWithFreezer(produtoDto);
            model.addAttribute("success", "Produto cadastrado e associado com sucesso!");
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar produto: " + e.getMessage());
        }
        
        model.addAttribute("produtoDto", new ProdutoDTO());
        model.addAttribute("listaFreezers", freezerService.findAll());
        return "produtocadastro";
    }

    @GetMapping("/atualizar/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Produto> produtoOpt = produtoService.findById(id);
        if (produtoOpt.isPresent()) {
            model.addAttribute("produtoDto", convertToDto(produtoOpt.get()));
            model.addAttribute("listaFreezers", freezerService.findAll());
            return "produtoatualizar";
        }
        return "redirect:/produto/lista";
    }

    @PostMapping("/atualizar/{id}")
    public String processUpdate(@PathVariable Long id, @ModelAttribute("produtoDto") ProdutoDTO produtoDto, Model model) {
        try {
            Produto produto = new Produto();
            produto.setNome(produtoDto.getNome());
            produto.setDataValidade(produtoDto.getDataValidade());
            produto.setTempMinima(produtoDto.getTemperaturaMinima());
            produto.setTempMaxima(produtoDto.getTemperaturaMaxima());
            // Note: Freezer association for update might need more logic in service, but for now:
            produtoService.update(id, produto);
            return "redirect:/produto/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao atualizar produto: " + e.getMessage());
            model.addAttribute("listaFreezers", freezerService.findAll());
            return "produtoatualizar";
        }
    }

    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDataValidade(produto.getDataValidade());
        dto.setTemperaturaMinima(produto.getTempMinima());
        dto.setTemperaturaMaxima(produto.getTempMaxima());
        
        if (produto.getFreezers() != null && !produto.getFreezers().isEmpty()) {
            Freezer freezer = produto.getFreezers().get(0);
            dto.setFreezerId(freezer.getId());
            dto.setFreezerNome(freezer.getNome());
        }
        
        return dto;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

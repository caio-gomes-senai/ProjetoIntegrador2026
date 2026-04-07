package com.senai.backend.controller;

import com.senai.backend.dto.ProdutoDTO;
import com.senai.backend.service.FreezerService;
import com.senai.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class WebProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FreezerService freezerService;

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
        
        // Return to the same page but empty form and updated message
        model.addAttribute("produtoDto", new ProdutoDTO());
        model.addAttribute("listaFreezers", freezerService.findAll());
        return "produtocadastro";
    }
}

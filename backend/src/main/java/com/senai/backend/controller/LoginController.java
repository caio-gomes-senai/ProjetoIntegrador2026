package com.senai.backend.controller;

import com.senai.backend.dto.LoginDTO;
import com.senai.backend.dto.UsuarioDTO;
import com.senai.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginDto") LoginDTO loginDto, Model model) {
        UsuarioDTO userOpt = loginService.login(loginDto);
        
        if (userOpt != null) {
            return "redirect:/home";
        }
        
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("usuarioDto", new UsuarioDTO());
        return "cadastroLogin";
    }

    @PostMapping("/cadastro")
    public String processCadastro(@ModelAttribute("usuarioDto") UsuarioDTO usuarioDto, Model model) {
        try {
            loginService.cadastrarUsuario(usuarioDto);
            return "redirect:/login"; // Vai para a tela de login para provar que a conta foi feita
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "cadastroLogin";
        }
    }
}

package com.senai.backend.controller;

import com.senai.backend.dto.FreezerDTO;
import com.senai.backend.model.Freezer;
import com.senai.backend.service.FreezerService;
import com.senai.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/refrigerador")
public class WebFreezerController {

    @Autowired
    private FreezerService freezerService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listFreezers(Model model) {
        List<FreezerDTO> dtos = freezerService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        model.addAttribute("listaFreezers", dtos);
        return "refrigerador";
    }

    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("freezerDto", new FreezerDTO());
        // For now, listing sensors is not implemented in service, but we would need it:
        // model.addAttribute("listaSensores", sensorService.findAll());
        return "refrigeradorCadastro";
    }

    @PostMapping("/cadastro")
    public String processCadastro(@ModelAttribute("freezerDto") FreezerDTO freezerDto) {
        Freezer freezer = new Freezer();
        freezer.setNome(freezerDto.getNome());

        // TODO: Substituir por usuário da sessão quando o login estiver finalizado
        usuarioRepository.findById(1L).ifPresent(freezer::setUsuario);

        freezerService.save(freezer);
        return "redirect:/refrigerador";
    }

    @GetMapping("/atualizar/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Freezer> freezerOpt = freezerService.findById(id);
        if (freezerOpt.isPresent()) {
            model.addAttribute("freezerDto", convertToDto(freezerOpt.get()));
            return "refrigeradorAtualizar";
        }
        return "redirect:/refrigerador";
    }

    @PostMapping("/atualizar/{id}")
    public String processUpdate(@PathVariable Long id, @ModelAttribute("freezerDto") FreezerDTO freezerDto) {
        Freezer freezer = new Freezer();
        freezer.setNome(freezerDto.getNome());
        freezerService.update(id, freezer);
        return "redirect:/refrigerador";
    }

    private FreezerDTO convertToDto(Freezer freezer) {
        return FreezerDTO.builder()
                .id(freezer.getId())
                .nome(freezer.getNome())
                .build();
    }
}

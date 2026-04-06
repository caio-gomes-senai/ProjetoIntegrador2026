package com.senai.backend.service;

import com.senai.backend.dto.LoginDTO;
import com.senai.backend.dto.UsuarioDTO;
import com.senai.backend.model.Usuario;
import com.senai.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO login(LoginDTO loginDto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginDto.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getSenha().equals(loginDto.getSenha())) {
                return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), null);
            }
        }
        return null;
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(dto.getSenha());

        Usuario salvo = usuarioRepository.save(novoUsuario);

        return new UsuarioDTO(salvo.getId(), salvo.getNome(), salvo.getEmail(), null);
    }
}
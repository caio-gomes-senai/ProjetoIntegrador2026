package com.senai.backend.config;

import com.senai.backend.model.Usuario;
import com.senai.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedDataConfig {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario teste = new Usuario();
                teste.setNome("Administrador");
                teste.setEmail("admin@admin.com");
                teste.setSenha("123456");
                usuarioRepository.save(teste);
                System.out.println("Usuário de teste criado: admin@admin.com / 123456");
            }
        };
    }
}

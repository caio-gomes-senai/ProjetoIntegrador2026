package com.senai.backend.service;

import com.senai.backend.model.Freezer;
import com.senai.backend.repository.FreezerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FreezerService {
    
    @Autowired
    private FreezerRepository freezerRepository;

    public List<Freezer> findAll() {
        return freezerRepository.findAll();
    }

    public Optional<Freezer> findById(Long id) {
        return freezerRepository.findById(id);
    }

    public Freezer save(Freezer freezer) {
        return freezerRepository.save(freezer);
    }

    public Freezer update(Long id, Freezer freezerAtualizado) {
        Optional<Freezer> freezerExistente = freezerRepository.findById(id);
        
        if (freezerExistente.isPresent()) {
            Freezer freezer = freezerExistente.get();
            freezer.setNome(freezerAtualizado.getNome());
            freezer.setUsuario(freezerAtualizado.getUsuario());
            return freezerRepository.save(freezer);
        }
        return null;
    }

    public void delete(Long id) {
        freezerRepository.deleteById(id);
    }
}
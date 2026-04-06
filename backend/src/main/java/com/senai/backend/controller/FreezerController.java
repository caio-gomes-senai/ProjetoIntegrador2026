package com.senai.backend.controller;

import com.senai.backend.model.Freezer;
import com.senai.backend.service.FreezerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post-freezers")
@CrossOrigin(origins = "*")
public class FreezerController {

    @Autowired
    private FreezerService freezerService;

    @GetMapping
    public ResponseEntity<List<Freezer>> getAll() {
        return ResponseEntity.ok(freezerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Freezer> getById(@PathVariable Long id) {
        Optional<Freezer> freezer = freezerService.findById(id);
        return freezer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // adicinar um verificalçao caso o sensor esteja rodando ele nao pode ser
    // excluido
    // e na pode ser cadastrado em mais de um freezer
    @PostMapping
    public ResponseEntity<Freezer> create(@Valid @RequestBody Freezer freezer) {
        Freezer saved = freezerService.save(freezer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Freezer> update(@PathVariable Long id, @Valid @RequestBody Freezer freezer) {
        Freezer updated = freezerService.update(id, freezer);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        freezerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
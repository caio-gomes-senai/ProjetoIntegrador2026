package com.senai.backend.controller;


import com.senai.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FreezerService freezerService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        
        dashboard.put("totalProdutos", produtoService.findAll().size());
        dashboard.put("totalFreezers", freezerService.findAll().size());
        dashboard.put("produtos", produtoService.findAll());
        dashboard.put("freezers", freezerService.findAll());
        dashboard.put("status", "OK");
        
        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalProdutos", produtoService.findAll().size());
        stats.put("totalFreezers", freezerService.findAll().size());
        stats.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(stats);
    }
}
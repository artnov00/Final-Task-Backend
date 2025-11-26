package com.nexus.gaming.controller;

import com.nexus.gaming.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

// 1. Repositório (Acesso ao Banco)
interface ProdutoRepository extends JpaRepository<Produto, Long> {}

// 2. Controlador (API)
@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}
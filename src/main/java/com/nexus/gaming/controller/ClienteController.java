package com.nexus.gaming.controller;

import com.nexus.gaming.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

// 1. Repositório
interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}

// 2. DTO para Login (Simples)
record LoginDTO(String email, String senha) {}

// 3. Controlador
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @PostMapping("/login")
    public ResponseEntity<Cliente> login(@RequestBody LoginDTO login) {
        Optional<Cliente> clienteOpt = repository.findByEmail(login.email());

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (cliente.getSenha().equals(login.senha())) {
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.status(401).build(); // Não autorizado
    }
}
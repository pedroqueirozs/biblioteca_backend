package com.biblioteca.digital.biblioteca.controller;

import com.biblioteca.digital.biblioteca.model.Operador;
import com.biblioteca.digital.biblioteca.service.OperadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/operadores")
@CrossOrigin(origins = "*")
public class OperadorController {

    private final OperadorService operadorService;

    public OperadorController(OperadorService operadorService) {
        this.operadorService = operadorService;
    }

    @GetMapping
    public ResponseEntity<List<Operador>> listarTodos() {
        return ResponseEntity.ok(operadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(operadorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Operador> criar(@RequestBody Operador operador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(operadorService.salvar(operador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operador> atualizar(@PathVariable Long id, @RequestBody Operador operador) {
        return ResponseEntity.ok(operadorService.atualizar(id, operador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        operadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.biblioteca.digital.biblioteca.controller;

import com.biblioteca.digital.biblioteca.model.Emprestimo;
import com.biblioteca.digital.biblioteca.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emprestimos")
@CrossOrigin(origins = "*")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Emprestimo>> listarAtivos() {
        return ResponseEntity.ok(emprestimoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Emprestimo>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(emprestimoService.listarPorUsuario(usuarioId));
    }

    // Corpo esperado: { "usuarioId": 1, "livroId": 2 }
    @PostMapping
    public ResponseEntity<Emprestimo> realizar(@RequestBody Map<String, Long> body) {
        Long usuarioId = body.get("usuarioId");
        Long livroId = body.get("livroId");
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.realizar(usuarioId, livroId));
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.devolver(id));
    }
}
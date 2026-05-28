package com.biblioteca.digital.biblioteca.service;

import com.biblioteca.digital.biblioteca.model.Operador;
import com.biblioteca.digital.biblioteca.repository.OperadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperadorService {

    private final OperadorRepository operadorRepository;

    public OperadorService(OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

    public List<Operador> listarTodos() {
        return operadorRepository.findAll();
    }

    public Operador buscarPorId(Long id) {
        return operadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operador não encontrado com id: " + id));
    }

    public Operador salvar(Operador operador) {
        return operadorRepository.save(operador);
    }

    public Operador atualizar(Long id, Operador dados) {
        Operador operador = buscarPorId(id);
        operador.setNome(dados.getNome());
        operador.setEmail(dados.getEmail());
        operador.setTelefone(dados.getTelefone());
        operador.setCargo(dados.getCargo());
        operador.setMatricula(dados.getMatricula());
        return operadorRepository.save(operador);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        operadorRepository.deleteById(id);
    }
}
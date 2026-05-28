package com.biblioteca.digital.biblioteca.service;

import com.biblioteca.digital.biblioteca.model.Livro;
import com.biblioteca.digital.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public List<Livro> listarDisponiveis() {
        return livroRepository.findByQuantidadeDisponivelGreaterThan(0);
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Livro salvar(Livro livro) {
        livro.setQuantidadeDisponivel(livro.getQuantidadeTotal());
        return livroRepository.save(livro);
    }

    public Livro atualizar(Long id, Livro dados) {
        Livro livro = buscarPorId(id);
        livro.setTitulo(dados.getTitulo());
        livro.setAutor(dados.getAutor());
        livro.setIsbn(dados.getIsbn());
        livro.setGenero(dados.getGenero());
        livro.setAnoPublicacao(dados.getAnoPublicacao());
        livro.setQuantidadeTotal(dados.getQuantidadeTotal());
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        livroRepository.deleteById(id);
    }
}
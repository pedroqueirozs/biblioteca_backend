package com.biblioteca.digital.biblioteca.service;

import com.biblioteca.digital.biblioteca.exception.RegraDeNegocioException;
import com.biblioteca.digital.biblioteca.model.Emprestimo;
import com.biblioteca.digital.biblioteca.model.Livro;
import com.biblioteca.digital.biblioteca.model.Usuario;
import com.biblioteca.digital.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.digital.biblioteca.repository.LivroRepository;
import com.biblioteca.digital.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             UsuarioRepository usuarioRepository,
                             LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByAtivo(true);
    }

    public List<Emprestimo> listarPorUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId);
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com id: " + id));
    }

    // Regra de negócio: só empresta se houver exemplares disponíveis
    public Emprestimo realizar(Long usuarioId, Long livroId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuarioId));

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + livroId));

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RegraDeNegocioException("Não há exemplares disponíveis para: " + livro.getTitulo());
        }

        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimo.realizar(); // método da interface Emprestavel

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolver(Long emprestimoId) {
        Emprestimo emprestimo = buscarPorId(emprestimoId);

        if (!emprestimo.estaAtivo()) {
            throw new RegraDeNegocioException("Este empréstimo já foi devolvido.");
        }

        emprestimo.devolver(); // método da interface Emprestavel

        Livro livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }
}
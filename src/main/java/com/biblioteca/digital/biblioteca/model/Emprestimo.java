package com.biblioteca.digital.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

// Emprestimo implementa Emprestavel: é OBRIGADO a ter realizar(), devolver() e estaAtivo()
// Isso é polimorfismo — qualquer código que receba um Emprestavel pode chamar esses métodos
// sem saber que é um Emprestimo especificamente.
@Entity
@Table(name = "emprestimos")
public class Emprestimo implements Emprestavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolvido;
    private boolean ativo;

    public Emprestimo() {}

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    @Override
    public void realizar() {
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(14);
        this.ativo = true;
    }

    @Override
    public void devolver() {
        this.dataDevolvido = LocalDate.now();
        this.ativo = false;
    }

    @Override
    public boolean estaAtivo() {
        return ativo;
    }

    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) { this.dataPrevistaDevolucao = dataPrevistaDevolucao; }

    public LocalDate getDataDevolvido() { return dataDevolvido; }
    public void setDataDevolvido(LocalDate dataDevolvido) { this.dataDevolvido = dataDevolvido; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
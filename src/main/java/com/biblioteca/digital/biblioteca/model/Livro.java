package com.biblioteca.digital.biblioteca.model;

import jakarta.persistence.*;

// Encapsulamento: todos os campos são private.
// O acesso externo só é permitido pelos métodos getters e setters.
// Isso protege o estado interno do objeto.
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro() {}

    public Livro(String titulo, String autor, String isbn, String genero, int anoPublicacao, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidade;
        this.quantidadeDisponivel = quantidade;
    }

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public int getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(int quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
}
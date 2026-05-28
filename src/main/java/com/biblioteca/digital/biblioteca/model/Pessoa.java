package com.biblioteca.digital.biblioteca.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// Classe abstrata: não pode ser instanciada diretamente (new Pessoa() não compila).
// @MappedSuperclass: o JPA herda os campos para as tabelas das subclasses.
// O método getTipoPessoa() é abstrato — cada subclasse OBRIGA a implementá-lo.
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;

    public Pessoa() {}

    public Pessoa(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Método abstrato: força subclasses a identificar seu tipo (polimorfismo)
    public abstract String getTipoPessoa();

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
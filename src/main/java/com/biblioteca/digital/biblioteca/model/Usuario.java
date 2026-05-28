package com.biblioteca.digital.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Herança: Usuario extends Pessoa — herda id, nome, email, telefone
// e é obrigado a implementar getTipoPessoa()
@Entity
@Table(name = "usuarios")
public class Usuario extends Pessoa {

    private String matricula;

    // JsonIgnore evita loop infinito na serialização JSON (Usuario->Emprestimo->Usuario->...)
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome, String email, String telefone, String matricula) {
        super(nome, email, telefone);
        this.matricula = matricula;
    }

    // Polimorfismo: cada subclasse retorna seu próprio tipo
    @Override
    public String getTipoPessoa() {
        return "USUARIO";
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public List<Emprestimo> getEmprestimos() { return emprestimos; }
    public void setEmprestimos(List<Emprestimo> emprestimos) { this.emprestimos = emprestimos; }
}
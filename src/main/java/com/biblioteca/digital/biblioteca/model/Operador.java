package com.biblioteca.digital.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Herança: Operador também extends Pessoa, mas tem seu próprio campo "cargo"
// Mesmo método abstrato, implementação diferente = polimorfismo
@Entity
@Table(name = "operadores")
public class Operador extends Pessoa {

    private String cargo;
    private String matricula;

    public Operador() {}

    public Operador(String nome, String email, String telefone, String cargo, String matricula) {
        super(nome, email, telefone);
        this.cargo = cargo;
        this.matricula = matricula;
    }

    @Override
    public String getTipoPessoa() {
        return "OPERADOR";
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}
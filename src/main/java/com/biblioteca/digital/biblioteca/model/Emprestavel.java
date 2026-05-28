package com.biblioteca.digital.biblioteca.model;

// Interface: define um CONTRATO. Qualquer classe que implemente Emprestavel
// é obrigada a ter esses três métodos. Isso é polimorfismo de interface.
public interface Emprestavel {

    void realizar();

    void devolver();

    boolean estaAtivo();
}
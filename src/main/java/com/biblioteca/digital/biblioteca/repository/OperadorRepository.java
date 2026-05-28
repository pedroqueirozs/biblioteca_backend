package com.biblioteca.digital.biblioteca.repository;

import com.biblioteca.digital.biblioteca.model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {
    Optional<Operador> findByEmail(String email);
    Optional<Operador> findByMatricula(String matricula);
}
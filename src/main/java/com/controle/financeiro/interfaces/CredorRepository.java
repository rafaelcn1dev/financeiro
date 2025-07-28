package com.controle.financeiro.interfaces;

import com.controle.financeiro.model.Credor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredorRepository extends JpaRepository<Credor, Long> {
    boolean existsByNome(String nome);
}

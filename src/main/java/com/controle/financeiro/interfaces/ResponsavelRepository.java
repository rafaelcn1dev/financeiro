package com.controle.financeiro.interfaces;

import com.controle.financeiro.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    boolean existsByNome(String nome);
}

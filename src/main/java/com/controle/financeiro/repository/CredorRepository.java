package com.controle.financeiro.repository;

import com.controle.financeiro.model.Credor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredorRepository extends JpaRepository<Credor, Long> {
}

package com.controle.financeiro.repository;

import com.controle.financeiro.model.Credor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredorRepository extends JpaRepository<Credor, Long> {
}

package com.controle.financeiro.interfaces;

import com.controle.financeiro.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
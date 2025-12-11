package com.controle.financeiro.interfaces;

import com.controle.financeiro.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCompraPai(Long compraPai);
}
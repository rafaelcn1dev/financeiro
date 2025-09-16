package com.controle.financeiro.services;


import com.controle.financeiro.interfaces.CompraRepository;
import com.controle.financeiro.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private static final Logger logger = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> listarTodos() {
        logger.info("Método listarTodos executado.");
        return compraRepository.findAll();
    }

    public Compra buscarCompraPorId(Long id) {
        logger.info("Método buscarCompraPorId executado. ID={}", id);
        return compraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compra com ID " + id + " não encontrado."));
    }

    public Compra inserirCompra(Compra Compra) {
        logger.info("Método inserirCompra executado.");
        Compra CompraSalvo = compraRepository.save(Compra);
        logger.info("Compra inserido com sucesso. ID gerado={}", CompraSalvo.getId());
        return CompraSalvo;
    }

    public Compra editarCompra(Long id, Compra CompraAtualizado) {
        logger.info("Método editarCompra executado. ID={}", id);
        Compra CompraExistente = compraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compra não encontrado."));
        // Atualiza os campos necessários
        CompraExistente.setDescricao(CompraAtualizado.getDescricao());
        CompraExistente.setValor(CompraAtualizado.getValor());
        CompraExistente.setDataCompra(CompraAtualizado.getDataCompra());
        CompraExistente.setCredor(CompraAtualizado.getCredor());
        CompraExistente.setParcelas(CompraAtualizado.getParcelas());
        Compra CompraEditado = compraRepository.save(CompraExistente);
        logger.info("Compra editado com sucesso. ID={}", CompraEditado.getId());
        return CompraEditado;
    }

    public void deletarCompra(Long id) {
        logger.info("Método deletarCompra executado. ID={}", id);
        if (!compraRepository.existsById(id)) {
            throw new IllegalArgumentException("Compra com ID " + id + " não encontrado para ser deletado.");
        }
        compraRepository.deleteById(id);
        logger.info("Compra deletado com sucesso. ID={}", id);
    }
}
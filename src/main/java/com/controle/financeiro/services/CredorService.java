package com.controle.financeiro.services;


import com.controle.financeiro.interfaces.CredorRepository;
import com.controle.financeiro.model.Credor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredorService {

    private static final Logger logger = LoggerFactory.getLogger(CredorService.class);

    @Autowired
    private CredorRepository credorRepository;

    public List<Credor> listarTodos() {
        logger.info("Método listarTodos executado.");
        return credorRepository.findAll();
    }

    public Credor inserirCredor(Credor credor) {
        if (credorRepository.existsByNome(credor.getNome())) {
            throw new IllegalArgumentException("Já existe um credor com o nome: " + credor.getNome());
        }
        logger.info("Método inserirCredor executado. Detalhes: Nome={}, DiaDeVencimento={}",
                credor.getNome(), credor.getDiaDeVencimento());
        Credor credorSalvo = credorRepository.save(credor);
        logger.info("Credor inserido com sucesso. ID gerado={}", credorSalvo.getId());
        return credorSalvo;
    }

    public Credor editarCredor(Long id, Credor credorAtualizado) {
        if (credorRepository.existsByNome(credorAtualizado.getNome()) &&
                !credorRepository.findById(id).map(Credor::getNome).orElse("").equals(credorAtualizado.getNome())) {
            throw new IllegalArgumentException("Já existe um credor com o nome: " + credorAtualizado.getNome());
        }
        logger.info("Método editarCredor executado. ID={}, Nome={}, DiaDeVencimento={}",
                id, credorAtualizado.getNome(), credorAtualizado.getDiaDeVencimento());
        Credor credorExistente = credorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credor não encontrado."));
        credorExistente.setNome(credorAtualizado.getNome());
        credorExistente.setDiaDeVencimento(credorAtualizado.getDiaDeVencimento());
        Credor credorEditado = credorRepository.save(credorExistente);
        logger.info("Credor editado com sucesso. ID={}", credorEditado.getId());
        return credorEditado;
    }

    public void deletarCredor(Long id) {
        logger.info("Método deletarCredor executado. ID={}", id);
        if (!credorRepository.existsById(id)) {
            throw new IllegalArgumentException("Credor com ID " + id + " não encontrado para ser deletado.");
        }
        credorRepository.deleteById(id);
        logger.info("Credor deletado com sucesso. ID={}", id);
    }
}
package com.controle.financeiro.services;


import com.controle.financeiro.interfaces.ResponsavelRepository;
import com.controle.financeiro.model.Responsavel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {

    private static final Logger logger = LoggerFactory.getLogger(ResponsavelService.class);

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public List<Responsavel> listarTodos() {
        logger.info("Método listarTodos executado.");
        return responsavelRepository.findAll();
    }

    public Responsavel buscarResponsavelPorId(Long id) {
        logger.info("Método buscarResponsavelPorId executado. ID={}", id);
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Responsavel com ID " + id + " não encontrado."));
    }

    public Responsavel inserirResponsavel(Responsavel responsavel) {
        if (responsavelRepository.existsByNome(responsavel.getNome())) {
            throw new IllegalArgumentException("Já existe um responsavel com o nome: " + responsavel.getNome());
        }
        logger.info("Método inserirResponsavel executado. Detalhes: Nome={}",
                responsavel.getNome());
        Responsavel responsavelSalvo = responsavelRepository.save(responsavel);
        logger.info("Responsavel inserido com sucesso. ID gerado={}", responsavelSalvo.getId());
        return responsavelSalvo;
    }

    public Responsavel editarResponsavel(Long id, Responsavel responsavelAtualizado) {
        if (responsavelRepository.existsByNome(responsavelAtualizado.getNome()) &&
                !responsavelRepository.findById(id).map(Responsavel::getNome).orElse("").equals(responsavelAtualizado.getNome())) {
            throw new IllegalArgumentException("Já existe um responsavel com o nome: " + responsavelAtualizado.getNome());
        }
        logger.info("Método editarResponsavel executado. ID={}, Nome={}",
                id, responsavelAtualizado.getNome());
        Responsavel responsavelExistente = responsavelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Responsavel não encontrado."));
        responsavelExistente.setNome(responsavelAtualizado.getNome());
        Responsavel responsavelEditado = responsavelRepository.save(responsavelExistente);
        logger.info("Responsavel editado com sucesso. ID={}", responsavelEditado.getId());
        return responsavelEditado;
    }

    public void deletarResponsavel(Long id) {
        logger.info("Método deletarResponsavel executado. ID={}", id);
        if (!responsavelRepository.existsById(id)) {
            throw new IllegalArgumentException("Responsavel com ID " + id + " não encontrado para ser deletado.");
        }
        responsavelRepository.deleteById(id);
        logger.info("Responsavel deletado com sucesso. ID={}", id);
    }
}
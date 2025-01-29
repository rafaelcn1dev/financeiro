package com.controle.financeiro.service;

import com.controle.financeiro.model.Credor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.financeiro.repository.CredorRepository;

import java.util.List;

@Service
public class CredorService {

    @Autowired
    private CredorRepository credorRepository;


    public CredorService(CredorRepository credorRepository) {
        this.credorRepository = credorRepository;
    }

    // Criar ou Atualizar Credor
    public Credor salvarCredor(Credor credor) {
        return credorRepository.save(credor);
    }

    // Metodo listar todos
    public List<Credor> listarTodos() {
        return credorRepository.findAll();
    }

    // Buscar por ID
    public Credor buscarPorId(Long id) {
        return credorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credor não encontrado"));
    }

    // Excluir Credor
    public void excluirCredor(Long id) {
        credorRepository.deleteById(id);
    }


}

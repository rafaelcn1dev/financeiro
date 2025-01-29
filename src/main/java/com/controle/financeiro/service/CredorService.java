package com.controle.financeiro.service;

import com.controle.financeiro.dto.CredorDTO;
import com.controle.financeiro.model.Credor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.financeiro.repository.CredorRepository;

import java.util.List;

@Service
public class CredorService {

    @Autowired
    private CredorRepository credorRepository;

    // Converte Credor para CredorDTO
    private CredorDTO toDTO(Credor credor) {
        return new CredorDTO(credor.getId(), credor.getNome());
    }

    // Converte CredorDTO para Credor
    private Credor toEntity(CredorDTO credorDTO) {
        Credor credor = new Credor();
        credor.setNome(credorDTO.getNome());
        return credor;
    }

    // Métodos do serviço usando DTO
    public CredorDTO salvarCredor(CredorDTO credorDTO) {
        Credor credor = toEntity(credorDTO);
        credor = credorRepository.save(credor);
        return toDTO(credor);
    }

    public List<CredorDTO> listarTodos() {
        return credorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CredorDTO buscarPorId(Long id) {
        Credor credor = credorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credor não encontrado"));
        return toDTO(credor);
    }

    public CredorDTO atualizarCredor(Long id, CredorDTO credorDTO) {
        Credor credorExistente = credorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credor não encontrado"));
        credorExistente.setNome(credorDTO.getNome());
        credorRepository.save(credorExistente);
        return toDTO(credorExistente);
    }

    // Método para EXCLUIR credor
    public void excluirCredor(Long id) {
        if (!credorRepository.existsById(id)) {
            throw new RuntimeException("Credor não encontrado para exclusão");
        }
        credorRepository.deleteById(id);
    }
}



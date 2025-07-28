package com.controle.financeiro.controllers;

import com.controle.financeiro.dto.CredorDTO;
import com.controle.financeiro.model.Credor;
import com.controle.financeiro.services.CredorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credor")
public class CredorController {

    @Autowired
    private CredorService credorService;

    @GetMapping
    public List<CredorDTO> listarTodos() {
        return credorService.listarTodos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CredorDTO inserirCredor(@Valid @RequestBody CredorDTO credorDTO) {
        Credor credor = toEntity(credorDTO);
        return toDTO(credorService.inserirCredor(credor));
    }

    @PutMapping("/{id}")
    public CredorDTO editarCredor(@PathVariable Long id, @Valid @RequestBody CredorDTO credorDTO) {
        Credor credorAtualizado = toEntity(credorDTO);
        return toDTO(credorService.editarCredor(id, credorAtualizado));
    }

    @DeleteMapping("/{id}")
    public void deletarCredor(@PathVariable Long id) {
        credorService.deletarCredor(id);
    }

    private CredorDTO toDTO(Credor credor) {
        CredorDTO dto = new CredorDTO();
        dto.setId(credor.getId());
        dto.setNome(credor.getNome());
        dto.setDiaDeVencimento(credor.getDiaDeVencimento());
        return dto;
    }

    private Credor toEntity(CredorDTO dto) {
        Credor credor = new Credor();
        credor.setId(dto.getId());
        credor.setNome(dto.getNome());
        credor.setDiaDeVencimento(dto.getDiaDeVencimento());
        return credor;
    }
}
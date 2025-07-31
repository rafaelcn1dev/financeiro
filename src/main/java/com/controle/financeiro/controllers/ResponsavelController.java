package com.controle.financeiro.controllers;

import com.controle.financeiro.dto.ResponsavelDTO;
import com.controle.financeiro.model.Responsavel;
import com.controle.financeiro.services.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping
    public List<ResponsavelDTO> listarTodos() {
        return responsavelService.listarTodos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponsavelDTO buscarResponsavelPorId(@PathVariable Long id) {
        Responsavel responsavel = responsavelService.buscarResponsavelPorId(id);
        return toDTO(responsavel);
    }

    @PostMapping
    public ResponsavelDTO inserirResponsavel(@Valid @RequestBody ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = toEntity(responsavelDTO);
        return toDTO(responsavelService.inserirResponsavel(responsavel));
    }

    @PutMapping("/{id}")
    public ResponsavelDTO editarResponsavel(@PathVariable Long id, @Valid @RequestBody ResponsavelDTO responsavelDTO) {
        Responsavel responsavelAtualizado = toEntity(responsavelDTO);
        return toDTO(responsavelService.editarResponsavel(id, responsavelAtualizado));
    }

    @DeleteMapping("/{id}")
    public void deletarResponsavel(@PathVariable Long id) {
        responsavelService.deletarResponsavel(id);
    }

    private ResponsavelDTO toDTO(Responsavel responsavel) {
        ResponsavelDTO dto = new ResponsavelDTO();
        dto.setId(responsavel.getId());
        dto.setNome(responsavel.getNome());
        return dto;
    }

    private Responsavel toEntity(ResponsavelDTO dto) {
        Responsavel responsavel = new Responsavel();
        responsavel.setId(dto.getId());
        responsavel.setNome(dto.getNome());
        return responsavel;
    }
}
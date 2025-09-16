package com.controle.financeiro.controllers;

import com.controle.financeiro.dto.CompraDTO;
import com.controle.financeiro.model.Compra;
import com.controle.financeiro.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<CompraDTO> listarCompras() {
        return compraService.listarTodos().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private CompraDTO toDTO(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setDescricao(compra.getDescricao());
        dto.setValor(compra.getValor());
        dto.setDataCompra(compra.getDataCompra());
        dto.setParcela(compra.getParcela());
        dto.setParcelas(compra.getParcelas());
        dto.setVencimento(compra.getVencimento());
        dto.setSituacao(compra.getSituacao());
        // faltando adicionar o credor e responsavel
        return dto;
    }
}

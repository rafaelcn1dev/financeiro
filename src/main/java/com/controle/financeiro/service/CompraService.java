package com.controle.financeiro.service;

import com.controle.financeiro.dto.CompraDTO;
import com.controle.financeiro.model.Compra;
import com.controle.financeiro.repository.CompraRepository;
import com.controle.financeiro.repository.CredorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CredorRepository credorRepository;

    // Converte Compra para DTO
    private CompraDTO toDTO(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setCredorId(compra.getCredor().getId());
        dto.setDescricao(compra.getDescricao());
        dto.setData(compra.getData());
        dto.setVencimento(compra.getVencimento());
        dto.setValor(compra.getValor());
        dto.setStatusPagamento(compra.getStatusPagamento());
        dto.setTipoCompra(compra.getTipoCompra());
        dto.setQuantidadeParcelas(compra.getQuantidadeParcelas());
        return dto;
    }

    // Converte DTO para Compra
    private Compra toEntity(CompraDTO dto) {
        Compra compra = new Compra();
        compra.setCredor(credorRepository.findById(dto.getCredorId())
                .orElseThrow(() -> new RuntimeException("Credor não encontrado")));
        compra.setDescricao(dto.getDescricao());
        compra.setData(dto.getData());
        compra.setVencimento(dto.getVencimento());
        compra.setValor(dto.getValor());
        compra.setStatusPagamento(dto.getStatusPagamento());
        compra.setTipoCompra(dto.getTipoCompra());
        compra.setQuantidadeParcelas(dto.getQuantidadeParcelas());
        return compra;
    }

    // Cria uma nova compra
    public CompraDTO criarCompra(CompraDTO compraDTO) {
        Compra compra = toEntity(compraDTO);
        compra = compraRepository.save(compra);
        return toDTO(compra);
    }

    // Atualiza uma compra existente
    public CompraDTO atualizarCompra(Long id, CompraDTO compraDTO) {
        Compra compraExistente = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        Compra compraAtualizada = toEntity(compraDTO);
        compraAtualizada.setId(compraExistente.getId());
        compraAtualizada = compraRepository.save(compraAtualizada);
        return toDTO(compraAtualizada);
    }

    // Exclui uma compra
    public void excluirCompra(Long id) {
        compraRepository.deleteById(id);
    }

    // Lista todas as compras
    public List<CompraDTO> listarTodos() {
        return compraRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Busca uma compra por ID
    public CompraDTO buscarPorId(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        return toDTO(compra);
    }
}
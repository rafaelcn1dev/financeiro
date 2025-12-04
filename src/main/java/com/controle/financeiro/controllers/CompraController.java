package com.controle.financeiro.controllers;

import com.controle.financeiro.dto.CompraDTO;
import com.controle.financeiro.model.Compra;
import com.controle.financeiro.model.Credor;
import com.controle.financeiro.model.Responsavel;
import com.controle.financeiro.services.CompraService;
import com.controle.financeiro.services.CredorService;
import com.controle.financeiro.services.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private CredorService credorService;

    @Autowired
    private ResponsavelService responsavelService;

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
        dto.setDataCobranca(compra.getDataCobranca());
        dto.setParcela(compra.getParcela());
        dto.setParcelas(compra.getParcelas());
        dto.setRestante(compra.getRestante());
        dto.setVencimento(compra.getVencimento());
        dto.setSituacao(compra.getSituacao());
        dto.setCredor(compra.getCredor());
        dto.setResponsavel(compra.getResponsavel());
        return dto;
    }

    @PostMapping
    public CompraDTO inserirCompra(@Valid @RequestBody CompraDTO compraDTO) {
        Credor credor = this.credorService.buscarCredorPorId(compraDTO.getCredor().getId());
        compraDTO.setCredor(credor);
        List<Compra> compras = comprasParceladas(compraDTO);
        if (!compras.isEmpty()) {
            List<Compra> comprasSalvas = compras.stream().map(compra -> {
                Double valorDaParcela = compraDTO.getValor() / compra.getParcelas();
                compra.setValor(valorDaParcela);
                compra.setCredor(credor);
                Responsavel responsavel = this.responsavelService.buscarResponsavelPorId(compraDTO.getResponsavel().getId());
                compra.setResponsavel(responsavel);
                return compraService.inserirCompra(compra);
            }).collect(Collectors.toList());
            return toDTO(comprasSalvas.get(0)); // Retorna a primeira compra salva como exemplo
        }
        return compraDTO;
    }

    private List<Compra> comprasParceladas(CompraDTO compraDTO) {
        ArrayList<Compra> listaPagamentoCompraParcelada = new ArrayList<>();
        if (compraDTO.getParcelas() > 0) {
            for (int i = 0; i < compraDTO.getParcelas(); i++) {
                Compra compra = toEntity(compraDTO);
                compra.setParcela(i + 1);
                compra.setRestante(compra.getParcelas() - compra.getParcela() + 1);
                compra.setDataCobranca(calcularDataCobranca(compra.getDataCompra(), compra.getCredor(), compra.getParcela()));
                listaPagamentoCompraParcelada.add(compra);
            }

        }
        return listaPagamentoCompraParcelada;
    }



    private Date calcularDataCobranca(Date dataDaCompra, Credor credor, int parcela) {
        LocalDate data = dataDaCompra.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Integer melhorDia = credor != null ? credor.getMelhorDiaDeCompra() : null;
        int diaCompra = data.getDayOfMonth();
        if(diaCompra >= melhorDia) {
            assert credor != null;
            LocalDate dataParcela = data.plusMonths(parcela + 1).withDayOfMonth(credor.getDiaDeVencimento());
            return Date.from(dataParcela.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            assert credor != null;
            LocalDate dataParcela = data.plusMonths(parcela).withDayOfMonth(credor.getDiaDeVencimento());
            return Date.from(dataParcela.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    private Compra toEntity(CompraDTO compraDTO) {
        Compra compra = new Compra();
        compra.setId(compraDTO.getId());
        compra.setDescricao(compraDTO.getDescricao());
        compra.setValor(compraDTO.getValor());
        compra.setDataCompra(compraDTO.getDataCompra());
        compra.setDataCobranca(compraDTO.getDataCobranca());
        compra.setParcela(compraDTO.getParcela());
        compra.setParcelas(compraDTO.getParcelas());
        compra.setVencimento(compraDTO.getVencimento());
        compra.setSituacao(compraDTO.getSituacao());
        compra.setCredor(compraDTO.getCredor());
        compra.setResponsavel(compraDTO.getResponsavel());
        return compra;
    }
}

package com.controle.financeiro.dto;

import com.controle.financeiro.model.StatusPagamento;
import com.controle.financeiro.model.TipoCompra;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

public class CompraDTO {
    private Long id;

    @NotNull
    private Long credorId;

    @NotNull
    private String descricao;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalDate vencimento;

    private double valor;

    @NotNull
    private StatusPagamento statusPagamento;

    @NotNull
    private TipoCompra tipoCompra;

    private Integer quantidadeParcelas;

    // Validação customizada para quantidadeParcelas
    private boolean isParcelasValidas() {
        return tipoCompra != TipoCompra.PARCELADO || (quantidadeParcelas != null && quantidadeParcelas > 0);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCredorId() {
        return credorId;
    }

    public void setCredorId(Long credorId) {
        this.credorId = credorId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public TipoCompra getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(TipoCompra tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
}
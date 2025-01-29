package com.controle.financeiro.model;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
public class Compra extends Throwable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Credor credor;
    private String descricao;
    private LocalDate data;
    private LocalDate vencimento;
    private double valor;
    private StatusPagamento statusPagamento; // Usando o ENUM StatusPagamento criado anteriormente
    private TipoCompra tipoCompra;
    private Integer quantidadeParcelas; // Só será preenchido se for PARCELADO

    // Construtor com validação
    public Compra(
            Credor credor,
            String descricao,
            LocalDate data,
            LocalDate vencimento,
            double valor,
            StatusPagamento statusPagamento,
            TipoCompra tipoCompra,
            Integer quantidadeParcelas
    ) {
        this.credor = credor;
        this.descricao = descricao;
        this.data = data;
        this.vencimento = vencimento;
        this.valor = valor;
        this.statusPagamento = statusPagamento;
        this.tipoCompra = tipoCompra;

        // Validação para parcelas
        if (tipoCompra == tipoCompra.PARCELADO) {
            if (quantidadeParcelas == null || quantidadeParcelas <= 0) {
                throw new IllegalArgumentException("Quantidade de parcelas inválida para tipo PARCELADO");
            }
            this.quantidadeParcelas = quantidadeParcelas;
        } else {
            this.quantidadeParcelas = null; // Ignora o valor se não for PARCELADO
        }
    }


    // Getters e Setters (com validações)
    public void setTipoCompra(TipoCompra tipoCompra) {
        this.tipoCompra = tipoCompra;
        // Se o tipo mudar para não PARCELADO, zera as parcelas
        if (tipoCompra != TipoCompra.PARCELADO) {
            this.quantidadeParcelas = null;
        }
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        if (this.tipoCompra == TipoCompra.PARCELADO) {
            if (quantidadeParcelas == null || quantidadeParcelas <= 0) {
                throw new IllegalArgumentException("Quantidade de parcelas inválida para tipo PARCELADO");
            }
            this.quantidadeParcelas = quantidadeParcelas;
        } else {
            throw new IllegalStateException("Quantidade de parcelas só pode ser definida para tipo PARCELADO");
        }
    }

    // Outros getters e setters (gerados automaticamente ou manualmente)
    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
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

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }


}
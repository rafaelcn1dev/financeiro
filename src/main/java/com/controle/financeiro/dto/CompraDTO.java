package com.controle.financeiro.dto;

import com.controle.financeiro.model.Credor;
import com.controle.financeiro.model.Responsavel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class CompraDTO {

    private Long id;
    private Credor credor;
    private String descricao;
    private Date dataCompra;
    private Date dataCobranca;
    private Double valor;
    private int parcela;
    private int parcelas;
    private int restante;
    private int compraPai;
    private int vencimento;
    private String situacao;
    private Responsavel responsavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getRestante() {
        return restante;
    }

    public void setRestante(int restante) {
        this.restante = restante;
    }

    public int getVencimento() {
        return vencimento;
    }

    public void setVencimento(int vencimento) {
        this.vencimento = vencimento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataCobranca() {
        return dataCobranca;
    }

    public void setDataCobranca(Date dataCobranca) {
        this.dataCobranca = dataCobranca;
    }

    public int getCompraPai() {
        return compraPai;
    }

    public void setCompraPai(int compraPai) {
        this.compraPai = compraPai;
    }
}

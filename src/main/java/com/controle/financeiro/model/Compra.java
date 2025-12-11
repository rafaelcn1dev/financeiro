package com.controle.financeiro.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@SequenceGenerator(name = "compra_seq", sequenceName = "compra_seq", allocationSize = 1)
public class Compra {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credor_id")
    private Credor credor;
    private String descricao;
    private Date dataCompra;
    private Date dataCobranca;
    private Double valor;
    private int parcela;
    private int parcelas;
    private int restante;
    private Long compraPai;
    private int vencimento;
    private String situacao;
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

  /*  @PrePersist
    public void prePersist() {
        // com SEQUENCE o provedor geralmente já atribui o id antes do callback
        if (compraPai == null) {
            compraPai = id;
        }
    }*/

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

    public Long getCompraPai() {
        return compraPai;
    }

    public void setCompraPai(Long compraPai) {
        this.compraPai = compraPai;
    }
}

package com.controle.financeiro.dto;

public class CredorDTO {

    private Long id;
    private String nome;
    private Integer diaDeVencimento;
    private Integer melhorDiaDeCompra;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDiaDeVencimento() {
        return diaDeVencimento;
    }

    public void setDiaDeVencimento(Integer diaDeVencimento) {
        this.diaDeVencimento = diaDeVencimento;
    }

    public Integer getMelhorDiaDeCompra() {
        return melhorDiaDeCompra;
    }

    public void setMelhorDiaDeCompra(Integer melhorDiaDeCompra) {
        this.melhorDiaDeCompra = melhorDiaDeCompra;
    }
}

package com.controle.financeiro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer diaDeVencimento;

    private Integer melhorDiaDeCompra;

    public Credor(String nome, Integer diaDeVencimento, Integer melhorDiaDeCompra) {
        this.nome = nome;
        this.diaDeVencimento = diaDeVencimento;
        this.melhorDiaDeCompra = melhorDiaDeCompra;
    }

    public Credor() {

    }

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
        if (diaDeVencimento < 1 || diaDeVencimento > 31) {
            throw new IllegalArgumentException("O dia de vencimento deve estar entre 1 e 31.");
        }
        this.diaDeVencimento = diaDeVencimento;
    }

    public Integer getMelhorDiaDeCompra() {
        return melhorDiaDeCompra;
    }

    public void setMelhorDiaDeCompra(Integer melhorDiaDeCompra) {
        if (melhorDiaDeCompra < 1 || melhorDiaDeCompra > 31) {
            throw new IllegalArgumentException("O dia de vencimento deve estar entre 1 e 31.");
        }
        this.melhorDiaDeCompra = melhorDiaDeCompra;
    }

}

package com.controle.financeiro.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Credor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    // Relação One-to-Many com Compra
    @OneToMany(mappedBy = "credor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras = new ArrayList<>();

    // Construtores, Getters e Setters
    public Credor() {}

    public Credor(String nome) {
        this.nome = nome;
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

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    // Método auxiliar para adicionar/remover compras
    public void addCompra(Compra compra) {
        getCompras().add(compra);
        compra.setCredor(this);
    }

    public void removeCompra(Compra compra) {
        getCompras().remove(compra);
        compra.setCredor(null);
    }
}
package com.controle.financeiro;

import com.controle.financeiro.model.Compra;
import com.controle.financeiro.model.Credor;
import com.controle.financeiro.model.StatusPagamento;
import com.controle.financeiro.model.TipoCompra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompraTest {

    private Credor credor;

    @BeforeEach
    void setUp() {
        credor = new Credor();
        credor.setNome("Loja XYZ");
    }

    @Test
    void deveCriarCompraAvistaCorretamente() {
        // Arrange & Act
        Compra compraAvista = new Compra(
                credor,
                "Notebook",
                LocalDate.now(),
                LocalDate.now(),
                2500.00,
                StatusPagamento.PAGO,
                TipoCompra.AVISTA,
                null
        );

        // Assert
        assertThat(compraAvista.getCredor()).isEqualTo(credor);
        assertThat(compraAvista.getDescricao()).isEqualTo("Notebook");
        assertThat(compraAvista.getValor()).isEqualTo(2500.00);
        assertThat(compraAvista.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
        assertThat(compraAvista.getTipoCompra()).isEqualTo(TipoCompra.AVISTA);
        assertThat(compraAvista.getQuantidadeParcelas()).isNull();
    }

    @Test
    void deveCriarCompraParceladaCorretamente() {
        // Arrange & Act
        Compra compraParcelada = new Compra(
                credor,
                "Smartphone",
                LocalDate.now(),
                LocalDate.now(),
                1500.00,
                StatusPagamento.PENDENTE,
                TipoCompra.PARCELADO,
                12
        );

        // Assert
        assertThat(compraParcelada.getTipoCompra()).isEqualTo(TipoCompra.PARCELADO);
        assertThat(compraParcelada.getQuantidadeParcelas()).isEqualTo(12);
        assertThat(compraParcelada.getStatusPagamento()).isEqualTo(StatusPagamento.PENDENTE);
    }

    @Test
    void deveCriarCompraMensalCorretamente() {
        // Arrange & Act
        Compra compraMensal = new Compra(
                credor,
                "Streaming",
                LocalDate.now(),
                LocalDate.now(),
                30.00,
                StatusPagamento.PAGO,
                TipoCompra.MENSAL,
                null
        );

        // Assert
        assertThat(compraMensal.getTipoCompra()).isEqualTo(TipoCompra.MENSAL);
        assertThat(compraMensal.getQuantidadeParcelas()).isNull();
        assertThat(compraMensal.getValor()).isEqualTo(30.00);
    }

    @Test
    void deveLancarExcecaoParaCompraParceladaSemParcelas() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            // A criação do objeto Compra deve estar DENTRO do bloco lambda
            new Compra(
                    credor,
                    "TV",
                    LocalDate.now(),
                    LocalDate.now(),
                    2000.00,
                    StatusPagamento.PENDENTE,
                    TipoCompra.PARCELADO,
                    null // Isso lançará a exceção automaticamente
            );
        });

        // Verifique a mensagem exata da exceção
        assertThat(exception.getMessage()).contains("Quantidade de parcelas inválida para tipo PARCELADO");
    }
}
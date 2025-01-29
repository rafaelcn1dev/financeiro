package com.controle.financeiro;

import com.controle.financeiro.model.Compra;
import com.controle.financeiro.model.Credor;
import com.controle.financeiro.model.StatusPagamento;
import com.controle.financeiro.model.TipoCompra;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class FinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);

			Credor credor = new Credor();
			credor.setNome("Loja XYZ");

			// Compra à vista
		Compra compraAvista = new Compra(
					credor,
					"Notebook",
					LocalDate.now(),
					LocalDate.now(),
					2500.00,
					StatusPagamento.PAGO,
					TipoCompra.AVISTA,
					null // Não precisa de parcelas
			);
		System.out.println(compraAvista.toString());

			// Compra parcelada (12x)
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
		System.out.println(compraParcelada.toString());

			// Compra mensal (ex: assinatura)
			Compra compraMensal = new Compra(
					credor,
					"Streaming",
					LocalDate.now(),
					LocalDate.now(),
					30.00,
					StatusPagamento.PAGO,
					TipoCompra.MENSAL,
					null // Não precisa de parcelas
			);

		System.out.println(compraMensal.toString());
		}
	}


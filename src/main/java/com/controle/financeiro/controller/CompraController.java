package com.controle.financeiro.controller;

import com.controle.financeiro.dto.CompraDTO;
import com.controle.financeiro.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    // Cria uma nova compra (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompraDTO criarCompra(@RequestBody CompraDTO compraDTO) {
        return compraService.criarCompra(compraDTO);
    }

    // Atualiza uma compra (PUT)
    @PutMapping("/{id}")
    public CompraDTO atualizarCompra(@PathVariable Long id, @RequestBody CompraDTO compraDTO) {
        return compraService.atualizarCompra(id, compraDTO);
    }

    // Exclui uma compra (DELETE)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCompra(@PathVariable Long id) {
        compraService.excluirCompra(id);
    }

    // Lista todas as compras (GET)
    @GetMapping
    public List<CompraDTO> listarTodos() {
        return compraService.listarTodos();
    }

    // Busca uma compra por ID (GET)
    @GetMapping("/{id}")
    public CompraDTO buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }
}
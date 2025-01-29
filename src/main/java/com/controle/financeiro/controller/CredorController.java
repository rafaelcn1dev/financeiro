package com.controle.financeiro.controller;

import com.controle.financeiro.model.Credor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.controle.financeiro.service.CredorService;

import java.util.List;

@RestController
@RequestMapping("/api/credores")
public class CredorController {

    @Autowired
    private CredorService credorService;

    // Criar Credor (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Credor criarCredor(@RequestBody Credor credor) {
        return credorService.salvarCredor(credor);
    }

    // Listar Todos (GET)
    @GetMapping
    public List<Credor> listarTodos() {
        return credorService.listarTodos();
    }

    // Buscar por ID (GET)
    @GetMapping("/{id}")
    public Credor buscarPorId(@PathVariable Long id) {
        return credorService.buscarPorId(id);
    }

    // Atualizar Credor (PUT)
    @PutMapping("/{id}")
    public Credor atualizarCredor(@PathVariable Long id, @RequestBody Credor credorAtualizado) {
        Credor credorExistente = credorService.buscarPorId(id);
        credorExistente.setNome(credorAtualizado.getNome());
        return credorService.salvarCredor(credorExistente);
    }

    // Excluir Credor (DELETE)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCredor(@PathVariable Long id) {
        credorService.excluirCredor(id);
    }
}

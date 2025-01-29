package com.controle.financeiro.controller;

import com.controle.financeiro.dto.CredorDTO;
import com.controle.financeiro.model.Credor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.controle.financeiro.service.CredorService;

import java.util.List;

@RestController
@RequestMapping("/credores")
public class CredorController {

    @Autowired
    private CredorService credorService;

    // Cria um novo credor (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CredorDTO criarCredor(@RequestBody CredorDTO credorDTO) {
        return credorService.salvarCredor(credorDTO);
    }

    // Lista todos (GET)
    @GetMapping
    public List<CredorDTO> listarTodos() {
        return credorService.listarTodos();
    }

    // Busca por ID (GET)
    @GetMapping("/{id}")
    public CredorDTO buscarPorId(@PathVariable Long id) {
        return credorService.buscarPorId(id);
    }

    // Atualiza um credor (PUT)
    @PutMapping("/{id}")
    public CredorDTO atualizarCredor(@PathVariable Long id, @RequestBody CredorDTO credorDTO) {
        return credorService.atualizarCredor(id, credorDTO);
    }

    // Exclui um credor (DELETE)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCredor(@PathVariable Long id) {
        credorService.excluirCredor(id);
    }
}

package com.controle.financeiro;

import com.controle.financeiro.interfaces.CredorRepository;
import com.controle.financeiro.interfaces.ResponsavelRepository;
import com.controle.financeiro.model.Credor;
import com.controle.financeiro.model.Responsavel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CredorRepository credorRepository;
    private final ResponsavelRepository responsavelRepository;

    public DataInitializer(CredorRepository credorRepository, ResponsavelRepository responsavelRepository) {
        this.credorRepository = credorRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Credor> credores = List.of(
                new Credor("Banco do Brasil", 1, 20),
                new Credor("C6", 8, 4),
                new Credor("Amazon", 5, 25)
        );

        for (Credor credor : credores) {
            if (!credorRepository.existsByNome(credor.getNome())) {
                credorRepository.save(credor);
            }
        }

        List<Responsavel> responsaveis = List.of(
                new Responsavel("Fulano"),
                new Responsavel("Sicrano"),
                new Responsavel("Beltrano")
        );

        for (Responsavel responsavel : responsaveis) {
            if (!responsavelRepository.existsByNome(responsavel.getNome())) {
                responsavelRepository.save(responsavel);
            }
        }

    }
}

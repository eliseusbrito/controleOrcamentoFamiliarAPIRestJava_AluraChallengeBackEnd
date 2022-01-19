package com.eliseubrito.controleOrcamentoFamiliar.config;

import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.repository.DespesaRepository;
import com.eliseubrito.controleOrcamentoFamiliar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;


@Configuration
@Profile("testEmH2")
public class ProfileTestEmH2Config implements CommandLineRunner {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Override
    public void run(String... args) throws Exception {
        Despesa d1 = new Despesa(1L, "Supermercado", 190.83, LocalDateTime.of(LocalDate.of(2021, 01, 03), LocalTime.of(18, 12, 45, 3546)));
        Despesa d2 = new Despesa(2L, "Barbeiro", 40.00, LocalDateTime.of(LocalDate.of(2021, 01, 02), LocalTime.of(17, 30 )));
        Despesa d3 = new Despesa(3L, "Alura", 90.00, LocalDateTime.of(LocalDate.of(2021, 01, 02), LocalTime.of(9, 12, 00 )));
        despesaRepository.saveAll(Arrays.asList(d1, d2, d3));
        Receita r1 = new Receita(1L, "Salario", 4985.00, LocalDateTime.of(LocalDate.of(2021, 01, 02), LocalTime.of(2, 35, 46 )));
        Receita r2 = new Receita(2L, "Imovel Alugado", 1000.00, LocalDateTime.of(LocalDate.of(2021, 01, 10), LocalTime.of(15, 10)));
        receitaRepository.saveAll(Arrays.asList(r1, r2));
    }

}

package com.eliseubrito.controleOrcamentoFamiliar.config;

import com.eliseubrito.controleOrcamentoFamiliar.enuns.Categoria;
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
        Despesa d1 = new Despesa(1L, "Supermercado", 190.83, LocalDateTime.of(LocalDate.of(2022, 01, 03), LocalTime.of(18, 12, 45, 3546)), Categoria.ALIMENTAÇÃO);
        Despesa d2 = new Despesa(2L, "Barbeiro", 40.00, LocalDateTime.of(LocalDate.of(2022, 01, 02), LocalTime.of(17, 30 )), Categoria.OUTRAS);
        Despesa d3 = new Despesa(3L, "Alura", 90.00, LocalDateTime.of(LocalDate.of(2022, 01, 02), LocalTime.of(9, 12)),Categoria.EDUCAÇÃO);
        Despesa d4 = new Despesa(4L, "Alura", 90.00, LocalDateTime.of(LocalDate.of(2022, 02, 02), LocalTime.of(9, 13)), Categoria.EDUCAÇÃO);
        despesaRepository.saveAll(Arrays.asList(d1, d2, d3, d4));
        Receita r1 = new Receita(1L, "Salário", 4985.00, LocalDateTime.of(LocalDate.of(2021, 12, 05), LocalTime.of(2, 35, 46 )));
        Receita r2 = new Receita(2L, "Imovel Alugado", 1000.00, LocalDateTime.of(LocalDate.of(2022, 01, 10), LocalTime.of(15, 10)));
        Receita r3 = new Receita(3L, "Salário", 5623.43, LocalDateTime.of(LocalDate.of(2021, 01, 05), LocalTime.of(8, 30)));
        Receita r4 = new Receita(4L, "Salário", 5624.44, LocalDateTime.of(LocalDate.of(2022, 01, 05), LocalTime.of(8, 31)));
        Receita r5 = new Receita(5L, "Salário", 5625.45, LocalDateTime.of(LocalDate.of(2022, 02, 05), LocalTime.of(8, 32)));
        Receita r6 = new Receita(6L, "Imovel Alugado", 1000.00, LocalDateTime.of(LocalDate.of(2022, 02, 10), LocalTime.of(15, 11)));
        Receita r7 = new Receita(7L, "Emprestimo", 500.00, LocalDateTime.of(LocalDate.of(2021, 12, 7), LocalTime.of(14, 25)));
        receitaRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7));
    }

}

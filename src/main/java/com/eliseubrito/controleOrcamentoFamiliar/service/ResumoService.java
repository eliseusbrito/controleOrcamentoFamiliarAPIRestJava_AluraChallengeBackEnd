package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.model.Resumo;
import com.eliseubrito.controleOrcamentoFamiliar.repository.DespesaRepository;
import com.eliseubrito.controleOrcamentoFamiliar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ResumoService {

    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    ReceitaRepository receitaRepository;

    public Resumo findByMes(Integer ano, Integer mes) {
        LocalDateTime startDate = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MIN, LocalTime.MIN)).withMonth(mes).withYear(ano).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime endDate = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MAX, LocalTime.MAX)).withMonth(mes).withYear(ano).with(TemporalAdjusters.lastDayOfMonth());

        List<Despesa> despesas = despesaRepository.findByAnoAndMes(startDate, endDate);
        double totalDespesas = 0;
        for (int i = 0; i < despesas.size(); i++) {
            totalDespesas = despesas.get(i).getValor() + totalDespesas;
        }
        Resumo resumo = new Resumo();
        resumo.setTotalDespesas(totalDespesas);

        List<Receita> receitas = receitaRepository.findByAnoAndMes(startDate, endDate);
        double totalReceitas = 0;
        for (int i = 0; i < receitas.size(); i++) {
            totalReceitas = receitas.get(i).getValor() + totalReceitas;
        }
        resumo.setTotalReceitas(totalReceitas);

        resumo.setSaldoFinalMes(totalReceitas - totalDespesas);

        return resumo;
    }

}

package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.enuns.Categoria;
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
        Resumo resumo = new Resumo();

        List<Despesa> despesas = despesaRepository.findByAnoAndMes(startDate, endDate);
        double totalDespesas = 0;
        for (int i = 0; i < despesas.size(); i++) {
            totalDespesas = despesas.get(i).getValor() + totalDespesas;
            if (despesas.get(i).getCategoria() == Categoria.ALIMENTAÇÃO) {
                double totalDespesasAlimentacao = 0;
                resumo.setTotalDespesasAlimentação(despesas.get(i).getValor() + totalDespesasAlimentacao);
            }
            if (despesas.get(i).getCategoria() == Categoria.SAÚDE) {
                double totalDespesasSaude = 0;
                resumo.setTotalDespesasSaude(despesas.get(i).getValor() + totalDespesasSaude);
            }
            if (despesas.get(i).getCategoria() == Categoria.MORADIA) {
                double totalDespesasMoradia = 0;
                resumo.setTotalDespesasMoradia(despesas.get(i).getValor() + totalDespesasMoradia);
            }
            if (despesas.get(i).getCategoria() == Categoria.TRANSPORTE) {
                double totalDespesasTransporte = 0;
                resumo.setTotalDespesasTransporte(despesas.get(i).getValor() + totalDespesasTransporte);
            }
            if (despesas.get(i).getCategoria() == Categoria.EDUCAÇÃO) {
                double totalDespesasEducacao = 0;
                resumo.setTotalDespesasEducacao(despesas.get(i).getValor() + totalDespesasEducacao);
            }
            if (despesas.get(i).getCategoria() == Categoria.LAZER) {
                double totalDespesasLazer = 0;
                resumo.setTotalDespesasLazer(despesas.get(i).getValor() + totalDespesasLazer);
            }
            if (despesas.get(i).getCategoria() == Categoria.IMPREVISTOS) {
                double totalDespesasImprevistos = 0;
                resumo.setTotalDespesasImprevistos(despesas.get(i).getValor() + totalDespesasImprevistos);
            }
            if (despesas.get(i).getCategoria() == Categoria.OUTRAS) {
                double totalDespesasOutras = 0;
                resumo.setTotalDespesasOutras(despesas.get(i).getValor() + totalDespesasOutras);
            }
        }

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

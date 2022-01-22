package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    public Iterable findAll(){
        return despesaRepository.findAll();
    }

    public Optional<Despesa> findById(Long id){
        return despesaRepository.findById(id);
    }

    public Despesa postDespesa(Despesa despesa) throws DescricaoDuplicadaException {
        String descricao = despesa.getDescricao();
        LocalDateTime data = despesa.getData();
        int month = data.getMonthValue();
        int year = data.getYear();
        Month mes = data.getMonth();
        LocalDateTime dateStart = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MIN, LocalTime.MIN)).withMonth(month).withYear(year).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime dateEnd = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MAX, LocalTime.MAX)).withMonth(month).withYear(year).with(TemporalAdjusters.lastDayOfMonth());
        Despesa existente =  despesaRepository.findByDescricaoAndMes(descricao, dateStart, dateEnd);
        if (existente != null){
            throw new DescricaoDuplicadaException(descricao, mes);
        }
        return despesaRepository.save(despesa);
    }

}

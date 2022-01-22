package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    public Iterable<Receita> findAll() {
        return receitaRepository.findAll();
    }

    public Optional<Receita> findById(Long id) {
        return receitaRepository.findById(id);
    }

    public Receita postReceita(Receita receita) throws Exception {
        Receita existente = verifyConditions(receita);
        if (existente != null) {
            String descricao = existente.getDescricao();
            Month mes = existente.getData().getMonth();
            throw new DescricaoDuplicadaException(descricao, mes);
        }
        return receitaRepository.save(receita);
    }

    public Receita update(Long id, Receita receita) throws DescricaoDuplicadaException {
        Optional<Receita> entity = receitaRepository.findById(id);
        Receita existente = new Receita();
        if(entity.isPresent()){
            existente = verifyConditions(receita);
        }
        if (existente != null) {
            String descricao = existente.getDescricao();
            Month mes = existente.getData().getMonth();
            throw new DescricaoDuplicadaException(descricao, mes);
        } else {
            Receita _receita = entity.get();
            _receita.setDescricao(receita.getDescricao());
            _receita.setValor(receita.getValor());
            _receita.setData(receita.getData());
            return receitaRepository.save(_receita);
        }
    }

    private Receita verifyConditions(Receita receita) {
        String descricao = receita.getDescricao();
        LocalDateTime data = receita.getData();
        int month = data.getMonthValue();
        int year = data.getYear();
        Month mes = data.getMonth();
        LocalDateTime dateStart = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MIN, LocalTime.MIN)).withMonth(month).withYear(year).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime dateEnd = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MAX, LocalTime.MAX)).withMonth(month).withYear(year).with(TemporalAdjusters.lastDayOfMonth());
        Receita existente = receitaRepository.findByDescricaoAndMes(descricao, dateStart, dateEnd);
        return existente;
    }

}

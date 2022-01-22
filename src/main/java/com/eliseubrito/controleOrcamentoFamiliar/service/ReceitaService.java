package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.exception.DatabaseException;
import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.exception.ResourceNotFoundException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Iterable<Receita> findAll(){
        return receitaRepository.findAll();
    }

    public Optional<Receita> findById(Long id){
        return receitaRepository.findById(id);
    }

    public Receita postReceita(Receita receita) throws Exception {
        String descricao = receita.getDescricao();
        LocalDateTime data = receita.getData();
        int month = data.getMonthValue();
        int year = data.getYear();
        Month mes = data.getMonth();
        LocalDateTime dateStart = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MIN, LocalTime.MIN)).withMonth(month).withYear(year).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime dateEnd = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MAX, LocalTime.MAX)).withMonth(month).withYear(year).with(TemporalAdjusters.lastDayOfMonth());
        Receita existente =  receitaRepository.findByDescricaoAndMes(descricao, dateStart, dateEnd);
        if (existente != null){
            throw new DescricaoDuplicadaException(descricao, mes);
        }
        return receitaRepository.save(receita);
    }

    public void delete(Long id) {
        try {
            receitaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}

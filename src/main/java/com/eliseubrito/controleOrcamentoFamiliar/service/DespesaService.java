package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.enuns.Categoria;
import com.eliseubrito.controleOrcamentoFamiliar.exception.DatabaseException;
import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.exception.ResourceNotFoundException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
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

    public List<Despesa> findByDescricao(String descricao){
        return despesaRepository.findByDescricaoIgnoreCaseContaining(descricao);
    }

    public Despesa postDespesa(Despesa despesa) throws DescricaoDuplicadaException {
        Despesa existente = verifyConditions(despesa);
        if (existente != null){
            String descricao = existente.getDescricao();
            Month mes = existente.getData().getMonth();
            throw new DescricaoDuplicadaException(descricao, mes);
        }
        if (despesa.getCategoria() == null){
            despesa.setCategoria(Categoria.OUTRAS);
        }
        return despesaRepository.save(despesa);
    }

    public Despesa update(Long id, Despesa despesa) throws DescricaoDuplicadaException {
        Optional<Despesa> entity = despesaRepository.findById(id);
        Despesa existente = new Despesa();
        if(entity.isPresent()){
            existente = verifyConditions(despesa);
        }
        if (existente != null) {
            String descricao = existente.getDescricao();
            Month mes = existente.getData().getMonth();
            throw new DescricaoDuplicadaException(descricao, mes);
        } else {
            Despesa _despesa = entity.get();
            _despesa.setDescricao(despesa.getDescricao());
            _despesa.setValor(despesa.getValor());
            _despesa.setData(despesa.getData());
            return despesaRepository.save(_despesa);
        }
    }

    public void delete(Long id) {
        try {
            despesaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private Despesa verifyConditions(Despesa despesa) {
        String descricao = despesa.getDescricao();
        LocalDateTime data = despesa.getData();
        int month = data.getMonthValue();
        int year = data.getYear();
        Month mes = data.getMonth();
        LocalDateTime dateStart = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MIN, LocalTime.MIN)).withMonth(month).withYear(year).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime dateEnd = LocalDateTime.now().with(LocalDateTime.of(LocalDate.MAX, LocalTime.MAX)).withMonth(month).withYear(year).with(TemporalAdjusters.lastDayOfMonth());
        Despesa existente = despesaRepository.findByDescricaoAndMes(descricao, dateStart, dateEnd);
        return existente;
    }

}

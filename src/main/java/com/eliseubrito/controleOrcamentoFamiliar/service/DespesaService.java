package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    public Iterable findAll(){
        return despesaRepository.findAll();
    }

    public Despesa postDespesa(Despesa despesa){
        return despesaRepository.save(despesa);
    }

}

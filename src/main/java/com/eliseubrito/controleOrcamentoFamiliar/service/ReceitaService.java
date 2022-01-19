package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    public Iterable<Receita> findAll(){
        return receitaRepository.findAll();
    }

    public Receita postReceita(Receita receita){
        return receitaRepository.save(receita);
    }

}

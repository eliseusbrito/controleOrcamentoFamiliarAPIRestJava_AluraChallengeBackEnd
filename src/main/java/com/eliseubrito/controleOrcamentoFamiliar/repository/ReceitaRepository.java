package com.eliseubrito.controleOrcamentoFamiliar.repository;

import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Long> {

}

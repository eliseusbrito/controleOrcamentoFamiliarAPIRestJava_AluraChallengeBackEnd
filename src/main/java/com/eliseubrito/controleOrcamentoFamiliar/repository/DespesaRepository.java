package com.eliseubrito.controleOrcamentoFamiliar.repository;

import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DespesaRepository extends CrudRepository<Despesa, Long> {

    @Query(value = "SELECT d FROM Despesa d where d.descricao = ?1 AND d.data between ?2 and ?3")
    Despesa findByDescricaoAndMes(String descricao, LocalDateTime dateStart, LocalDateTime  dateEnd);

    List<Despesa> findByDescricaoIgnoreCaseContaining(String descricao);

}

package com.eliseubrito.controleOrcamentoFamiliar.repository;

import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Long> {

    @Query(value = "SELECT r FROM Receita r where r.descricao = ?1 AND r.data between ?2 and ?3")
    Receita findByDescricaoAndMes(String descricao, LocalDateTime dateStart, LocalDateTime  dateEnd);

    List<Receita> findByDescricaoIgnoreCaseContaining(String descricao);

}

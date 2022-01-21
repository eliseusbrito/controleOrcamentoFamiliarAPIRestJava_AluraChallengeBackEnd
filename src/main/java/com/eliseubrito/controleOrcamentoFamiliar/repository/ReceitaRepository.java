package com.eliseubrito.controleOrcamentoFamiliar.repository;

import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Long> {

//    @Query(value = "SELECT * FROM TB_DESPESA tb where tb.DESCRICAO = ?1 AND tb.DATA = '2021-01-01T08:30:51.65'", nativeQuery = true)
//    Receita findByDescricaoAndMes(String descricao, LocalDate dateStart);

//    @Query(value = "SELECT * FROM TB_DESPESA tb where tb.DESCRICAO = ?1 AND tb.DATA BETWEEN ?2 AND ?3", nativeQuery = true)
//    Receita findByDescricaoAndMes(String descricao, LocalDateTime dateStart, LocalDateTime  dateEnd);

//    @Query(value = "SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = ?1", nativeQuery = true)
//    Receita findByDescricaoAndMes(String descricao);

//    @Query(value = "SELECT * FROM TB_DESPESA tb where tb.DESCRICAO = ?1 AND tb.DATA >= ?2 AND tb.DATA <= ?3", nativeQuery = true)
//    Receita findByDescricaoAndMes(String descricao, LocalDateTime dateStart, LocalDateTime  dateEnd);

//    @Query(value = "SELECT r FROM Receita r where r.descricao = ?1 AND r.data >= ?2 AND r.data <= ?3")
//    Receita findByDescricaoAndMes(String descricao, LocalDateTime dateStart, LocalDateTime  dateEnd);

    @Query(value = "SELECT r FROM Receita r where r.descricao = ?1 AND r.data between ?2 and ?3")
    Receita findByDescricaoAndMes(String descricao, LocalDateTime dateStart, LocalDateTime  dateEnd);

}

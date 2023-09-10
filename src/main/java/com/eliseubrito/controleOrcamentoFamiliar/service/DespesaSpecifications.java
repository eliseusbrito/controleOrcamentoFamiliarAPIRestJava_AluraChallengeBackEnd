package com.eliseubrito.controleOrcamentoFamiliar.service;

import com.eliseubrito.controleOrcamentoFamiliar.enuns.Categoria;
import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Date;

public class DespesaSpecifications {
    public static Specification<Despesa> searchByParameters(Long id, String descricao, Double valor, LocalDateTime data, Categoria categoria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (descricao != null && !descricao.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("descricao"), "%" + descricao + "%"));
            }

            if (valor != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("valor"), valor));
            }

            if (data != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("data"), data));
            }

            if (categoria != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("categoria"), categoria));
            }

            return predicate;
        };
    }
}


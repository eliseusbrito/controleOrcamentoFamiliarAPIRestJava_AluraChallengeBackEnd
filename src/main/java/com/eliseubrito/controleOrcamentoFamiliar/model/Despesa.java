package com.eliseubrito.controleOrcamentoFamiliar.model;

import com.eliseubrito.controleOrcamentoFamiliar.enuns.Categoria;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_despesa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String descricao;
    @Positive
    private double valor;
    @NotNull
    private LocalDateTime data;
    private Categoria categoria;

}

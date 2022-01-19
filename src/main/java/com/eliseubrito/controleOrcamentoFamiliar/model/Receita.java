package com.eliseubrito.controleOrcamentoFamiliar.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_receita")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Receita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double valor;
    private LocalDateTime data;

}

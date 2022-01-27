package com.eliseubrito.controleOrcamentoFamiliar.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resumo {

    private double totalReceitas;
    private double totalDespesas;
    private double saldoFinalMes;

}

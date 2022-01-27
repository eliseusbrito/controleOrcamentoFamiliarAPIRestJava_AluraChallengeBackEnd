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
    private double totalDespesasAlimentação;
    private double totalDespesasSaude;
    private double totalDespesasMoradia;
    private double totalDespesasTransporte;
    private double totalDespesasEducacao;
    private double totalDespesasLazer;
    private double totalDespesasImprevistos;
    private double totalDespesasOutras;

}

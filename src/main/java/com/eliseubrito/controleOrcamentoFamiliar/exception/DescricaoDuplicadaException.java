package com.eliseubrito.controleOrcamentoFamiliar.exception;

import java.time.Month;

public class DescricaoDuplicadaException extends Exception{

    protected String descricao;
    private final Month mes;

    public DescricaoDuplicadaException(String descricao, Month mes) {
        super();
        this.descricao = descricao;
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Descrição '" + descricao + "' já existente no mês de lançamento. Não pode ser duplicada dentro do mês de " + mes + ". ";
    }
}

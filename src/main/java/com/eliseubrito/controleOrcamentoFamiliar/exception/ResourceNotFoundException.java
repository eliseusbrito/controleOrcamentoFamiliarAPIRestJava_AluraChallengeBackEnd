package com.eliseubrito.controleOrcamentoFamiliar.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Associado id=" +id+" não encontrado no cadastro.");
    }

}

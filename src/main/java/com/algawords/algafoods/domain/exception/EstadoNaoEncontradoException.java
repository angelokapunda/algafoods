package com.algawords.algafoods.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de cidade com código %d", estadoId));
    }
}

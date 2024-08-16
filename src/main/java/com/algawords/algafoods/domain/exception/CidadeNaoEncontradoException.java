package com.algawords.algafoods.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException{
    public CidadeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradoException(Long cidadeId) {
        this(String.format("Não existe um cadastro de cidade com código %d", cidadeId));
    }

}

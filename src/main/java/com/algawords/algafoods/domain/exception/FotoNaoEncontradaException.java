package com.algawords.algafoods.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException{

    public FotoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoNaoEncontradaException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d ",
                produtoId, restauranteId));
    }
}

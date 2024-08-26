package com.algawords.algafoods.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long pedidoId) {
        this(String.format("Não exite pedido cadastrado de código %d" , pedidoId));
    }
}
package com.algawords.algafoods.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Não exite pedido cadastrado de código %s" , codigoPedido));
    }
}

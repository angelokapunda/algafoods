package com.algawords.algafoods.domain.exception;

public class PermissaoNaoEncontradaExcption extends EntidadeNaoEncontradaException{

    public PermissaoNaoEncontradaExcption(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaExcption(Long permissaoId) {
        this(String.format("Não existe permissão cadastrada de código ", permissaoId));
    }
}

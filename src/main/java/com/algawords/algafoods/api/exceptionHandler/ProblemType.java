package com.algawords.algafoods.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Endidade não encontrada"),
    ENTIDADE_EM_USO ("/entidade-em-uso", "Entidade Em Uso"),
    ERRO_DEE_NEGOCIO ("/erro-de-negocio", "Violação de regras de negócio"),
    PARAMETRO_INVALIDO("/parametro-invatido", "Parâmetro inválido"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("dados-invalidos", "Daods Inválidos");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafoods.com.br" + path;
        this.title = title;
    }
}

package com.algawords.algafoods.api.modelo;

import lombok.Data;

@Data
public class FotoProdutoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}

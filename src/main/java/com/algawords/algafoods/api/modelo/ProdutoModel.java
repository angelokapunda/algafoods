package com.algawords.algafoods.api.modelo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProdutoModel {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;
}

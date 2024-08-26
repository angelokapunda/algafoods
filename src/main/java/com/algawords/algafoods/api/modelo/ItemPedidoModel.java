package com.algawords.algafoods.api.modelo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoModel {

    private Long idProduto;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
}

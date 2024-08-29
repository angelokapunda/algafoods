package com.algawords.algafoods.api.modelo;

import com.algawords.algafoods.api.modelo.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteModel {

    @JsonView({RestauranteView.Rresumo.class, RestauranteView.apenasNome.class})
    private Long id;

    @JsonView({RestauranteView.Rresumo.class, RestauranteView.apenasNome.class})
    private String nome;

    @JsonView(RestauranteView.Rresumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Rresumo.class)
    private CozinhaModel cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;

}

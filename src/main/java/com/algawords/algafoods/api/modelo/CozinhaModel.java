package com.algawords.algafoods.api.modelo;

import com.algawords.algafoods.api.modelo.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class CozinhaModel {

    @JsonView(RestauranteView.Rresumo.class)
    private Long id;

    @JsonView(RestauranteView.Rresumo.class)
    private String nome;
}

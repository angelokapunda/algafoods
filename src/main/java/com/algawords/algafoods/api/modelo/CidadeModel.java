package com.algawords.algafoods.api.modelo;

import lombok.Data;

@Data
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;
}

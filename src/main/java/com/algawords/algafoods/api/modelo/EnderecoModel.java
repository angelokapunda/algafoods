package com.algawords.algafoods.api.modelo;

import lombok.Data;

@Data
public class EnderecoModel {

    private String cep;
    private String numero;
    private String complemento;
    private String logradouro;
    private String bairro;
    private CidadeResumoModel cidade;
}

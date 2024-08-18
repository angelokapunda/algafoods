package com.algawords.algafoods.api.modelo.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoInput {

    @NotBlank
    private String cep;

    @NotBlank
    private String numero;
    private String complemento;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;
}

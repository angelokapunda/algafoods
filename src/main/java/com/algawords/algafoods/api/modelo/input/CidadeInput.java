package com.algawords.algafoods.api.modelo.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CidadeInput {

    @NotBlank
    private String nome;

    @NotNull
    @Valid
    private EstadoIdInput estado;
}

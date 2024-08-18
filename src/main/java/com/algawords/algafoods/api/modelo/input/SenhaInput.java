package com.algawords.algafoods.api.modelo.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SenhaInput {

    @NotBlank
    private String senhaActual;

    @NotBlank
    private String novaSenha;


}

package com.algawords.algafoods.api.modelo.input;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UsuarioInputActualizar {

    private String nome;

    @Email
    private String email;


}

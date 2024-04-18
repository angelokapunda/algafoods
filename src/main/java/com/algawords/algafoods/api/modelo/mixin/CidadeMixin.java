package com.algawords.algafoods.api.modelo.mixin;

import com.algawords.algafoods.domain.modelo.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}

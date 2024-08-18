package com.algawords.algafoods.api.modelo.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CidadeIdInput {

    @NotNull
    private Long id;
}

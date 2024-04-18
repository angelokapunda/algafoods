package com.algawords.algafoods.core.jackson;

import com.algawords.algafoods.api.modelo.mixin.CidadeMixin;
import com.algawords.algafoods.api.modelo.mixin.CozinhaMixin;
import com.algawords.algafoods.api.modelo.mixin.RestauranteMixin;
import com.algawords.algafoods.domain.modelo.Cidade;
import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }


}

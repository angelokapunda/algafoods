package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.input.RestauranteInput;
import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.modelo.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDesassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
       return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {

        // Para evitar org.hibernate.HibernateException: identifier of an instance of com.algawords.algafoods.domain.modelo.Cozinha
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteInput, restaurante);
    }

}

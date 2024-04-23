package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.CozinhaModel;
import com.algawords.algafoods.api.modelo.RestauranteModel;
import com.algawords.algafoods.domain.modelo.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
       return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toModel)
                .collect(Collectors.toList());
    }

}

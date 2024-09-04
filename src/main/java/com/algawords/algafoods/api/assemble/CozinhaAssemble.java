package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.CozinhaModel;
import com.algawords.algafoods.domain.modelo.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaAssemble {

    @Autowired
    ModelMapper modelMapper;

    public CozinhaModel toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModel.class);
    }

    public List<CozinhaModel> toModel(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toModel)
                .collect(Collectors.toList());

    }
}

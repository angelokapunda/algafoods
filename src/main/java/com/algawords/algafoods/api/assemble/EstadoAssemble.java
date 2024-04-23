package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.EstadoModel;
import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.modelo.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoAssemble {


    @Autowired
    private ModelMapper modelMapper;

    public EstadoModel toModel(Estado estado) {
        return modelMapper.map(estado, EstadoModel.class);
    }

    public List<EstadoModel> lisToModel(List<Estado> estados) {
        return estados.stream().map(this::toModel)
                .collect(Collectors.toList());
    }
}

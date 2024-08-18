package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.GrupoModel;
import com.algawords.algafoods.domain.modelo.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrupoModelAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionModel(List<Grupo> grupos) {
        return grupos.stream().map(grupo -> toModel(grupo))
                .toList();
    }
}

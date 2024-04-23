package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.CidadeModel;
import com.algawords.algafoods.domain.modelo.Cidade;
import com.algawords.algafoods.domain.modelo.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }

    public List<CidadeModel> listToModel(List<Cidade> cidades) {
        return cidades.stream().map(this::toModel)
                .collect(Collectors.toList());
    }
}

package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.FotoProdutoModel;
import com.algawords.algafoods.domain.modelo.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssmble {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoModel.class);
    }
}

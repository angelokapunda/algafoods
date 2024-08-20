package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.ProdutoModel;
import com.algawords.algafoods.domain.modelo.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream().map(produto -> toModel(produto))
                .toList();
    }
}

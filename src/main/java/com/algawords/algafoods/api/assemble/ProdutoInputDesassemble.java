package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.input.ProdutoInput;
import com.algawords.algafoods.domain.modelo.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDesassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject (ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject (ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}

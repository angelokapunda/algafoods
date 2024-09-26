package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.modelo.FotoProduto;
import com.algawords.algafoods.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRespository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto){
        return produtoRespository.save(foto);
    }
}

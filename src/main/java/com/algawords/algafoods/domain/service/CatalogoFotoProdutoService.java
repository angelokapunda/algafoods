package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.modelo.FotoProduto;
import com.algawords.algafoods.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRespository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto){
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRespository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            produtoRespository.delete(fotoExistente.get());
        }
        return produtoRespository.save(foto);
    }
}

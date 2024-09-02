package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.ProdutoNaoEncontradoException;
import com.algawords.algafoods.domain.modelo.Produto;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    CadastroRestauranteService cadastroRestauranteService;

    @Transactional
    public Produto salvar (Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listar(Long restauranteId, boolean incluirInativos){
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        if (incluirInativos) {
            return produtoRepository.findByRestaurante(restaurante);
        } else {
            return produtoRepository.findAtivosByRestaurante(restaurante);
        }
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

}

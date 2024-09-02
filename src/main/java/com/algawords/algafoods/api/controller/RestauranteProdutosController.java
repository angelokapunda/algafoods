package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.ProdutoAssemble;
import com.algawords.algafoods.api.assemble.ProdutoInputDesassemble;
import com.algawords.algafoods.api.modelo.ProdutoModel;
import com.algawords.algafoods.api.modelo.input.ProdutoInput;
import com.algawords.algafoods.domain.modelo.Produto;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.service.CadastroProdutoService;
import com.algawords.algafoods.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoAssemble produtoAssemble;

    @Autowired
    private ProdutoInputDesassemble produtoInputDesassemble;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativos) {
        return produtoAssemble.toCollectionModel(cadastroProduto.listar(restauranteId, incluirInativos));
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar ( @PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        return produtoAssemble.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel cadastrar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Long restauranteId) {

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        Produto produto = produtoInputDesassemble.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        return produtoAssemble.toModel(cadastroProduto.salvar(produto));
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel actualizar (@PathVariable Long produtoId, @PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoActual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDesassemble.copyToDomainObject(produtoInput, produtoActual);
        return produtoAssemble.toModel(cadastroProduto.salvar(produtoActual));
    }
}

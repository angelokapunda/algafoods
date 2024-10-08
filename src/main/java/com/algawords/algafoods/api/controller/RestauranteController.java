package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.RestauranteInputDesassemble;
import com.algawords.algafoods.api.assemble.RestauranteModelAssemble;
import com.algawords.algafoods.api.modelo.RestauranteModel;
import com.algawords.algafoods.api.modelo.input.RestauranteInput;
import com.algawords.algafoods.api.modelo.view.RestauranteView;
import com.algawords.algafoods.domain.exception.CidadeNaoEncontradoException;
import com.algawords.algafoods.domain.exception.CozinhaNaoEncontradaException;
import com.algawords.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algawords.algafoods.domain.exception.NegocioException;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.repository.RestauranteRepository;
import com.algawords.algafoods.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteModelAssemble restauranteModelAssemble;

    @Autowired
    private RestauranteInputDesassemble restauranteInputDesassemble;

    @GetMapping
    @JsonView(RestauranteView.Rresumo.class)
    public List<RestauranteModel> lista () {
       return restauranteModelAssemble.toCollectionModel(cadastroRestaurante.listar());
    }

    @GetMapping(params = "projecao=apenas-nome")
    @JsonView(RestauranteView.apenasNome.class)
    public List<RestauranteModel> listaApenasNome () {
        return lista();
    }

    @GetMapping("/{id}")
    public RestauranteModel busca(@PathVariable Long id) {
        Restaurante restaurante =  cadastroRestaurante.buscarOuFalhar(id);
        return restauranteModelAssemble.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar (@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDesassemble.toDomainObject(restauranteInput);
            return restauranteModelAssemble.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage()) ;
        }
    }

    @PutMapping("/{id}")
    public RestauranteModel actualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteActual = cadastroRestaurante.buscarOuFalhar(id);
        restauranteInputDesassemble.copyToDomainObject(restauranteInput, restauranteActual);

        try {
            return restauranteModelAssemble.toModel(cadastroRestaurante.salvar(restauranteActual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir (@PathVariable Long restauranteId) {
        cadastroRestaurante.abrirRestaurante(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar (@PathVariable Long restauranteId) {
        cadastroRestaurante.fecharRestaurante(restauranteId);
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativo (@PathVariable Long restauranteId) {
        cadastroRestaurante.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativo (@PathVariable Long restauranteId) {
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestaurante.ativarLista(restauranteIds);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativoarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestaurante.inativarLista(restauranteIds);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroRestaurante.remover(id);
    }

}

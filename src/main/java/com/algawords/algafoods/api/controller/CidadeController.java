package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.CidadeAssemble;
import com.algawords.algafoods.api.assemble.CidadeInputDesassemble;
import com.algawords.algafoods.api.modelo.CidadeModel;
import com.algawords.algafoods.api.modelo.input.CidadeInput;
import com.algawords.algafoods.domain.exception.EstadoNaoEncontradoException;
import com.algawords.algafoods.domain.exception.NegocioException;
import com.algawords.algafoods.domain.modelo.Cidade;
import com.algawords.algafoods.domain.service.CadastroCidadesService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {


    @Autowired
    private CadastroCidadesService cadastroCidades;
    @Autowired
    private CidadeAssemble cidadeAssemble;
    @Autowired
    private CidadeInputDesassemble cidadeInputDesassemble;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDesassemble.toDomainObject(cidadeInput);
            return cidadeAssemble.toModel(cadastroCidades.cadastro(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeAssemble.listToModel(cadastroCidades.listar());
    }

    @GetMapping("/{id}")
    public CidadeModel buscar(@PathVariable Long id) {
        return cidadeAssemble.toModel(cadastroCidades.buscarOuFalhar(id));
    }

    @PutMapping("/{id}")
    public CidadeModel actualizar(@PathVariable Long id, @RequestBody CidadeInput cidadeInput) {
        Cidade cidadeActual = cadastroCidades.buscarOuFalhar(id);
        cidadeInputDesassemble.copyDomainObject(cidadeInput, cidadeActual);
//        BeanUtils.copyProperties(cidade, cidadeActual, "id");
        try {
            return cidadeAssemble.toModel(cadastroCidades.cadastro(cidadeActual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCidades.remove(id);
    }


}

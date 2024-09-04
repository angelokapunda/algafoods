package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.CozinhaAssemble;
import com.algawords.algafoods.api.assemble.CozinhaInputDesassemble;
import com.algawords.algafoods.api.modelo.CozinhaModel;
import com.algawords.algafoods.api.modelo.input.CozinhaInput;
import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.repository.CozinhaRepository;
import com.algawords.algafoods.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/cozinhas")
public class CozinhaController {


    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    CozinhaAssemble cozinhaAssemble;

    @Autowired
    private CozinhaInputDesassemble cozinhaInputDesassemble;

    @GetMapping
    public Page<CozinhaModel> lista(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhaPage = cozinhaRepository.findAll(pageable);
        List<CozinhaModel> cozinhasModel = cozinhaAssemble.toModel(cozinhaPage.getContent());

        return new PageImpl<>(cozinhasModel, pageable, cozinhaPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public CozinhaModel buscar(@PathVariable Long id) {
        return cozinhaAssemble.toModel(cadastroCozinha.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar (@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDesassemble.toDomainObject(cozinhaInput);
        return  cozinhaAssemble.toModel(cadastroCozinha.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaModel actualizar(@PathVariable Long id, @Valid @RequestBody CozinhaInput cozinhaInput) {

        Cozinha cozinhaActual = cadastroCozinha.buscarOuFalhar(id);
        cozinhaInputDesassemble.copyToDomainObject(cozinhaInput, cozinhaActual);

        return cozinhaAssemble.toModel(cadastroCozinha.salvar(cozinhaActual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCozinha.excluir(id);
    }



}


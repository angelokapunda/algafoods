package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.GrupoInputDesassemble;
import com.algawords.algafoods.api.assemble.GrupoModelAssemble;
import com.algawords.algafoods.api.modelo.GrupoModel;
import com.algawords.algafoods.api.modelo.input.GrupoInput;
import com.algawords.algafoods.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
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
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoModelAssemble grupoModelAssemble;

    @Autowired
    private GrupoInputDesassemble grupoInputDesassemble;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel cadastrar (@RequestBody @Valid GrupoInput grupoInput) {
        var grupo = cadastroGrupo.salvar(grupoInputDesassemble.toDomainObject(grupoInput));
        return grupoModelAssemble.toModel(grupo);
    }

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelAssemble.toCollectionModel(cadastroGrupo.listar());
    }

    @GetMapping("/{id}")
    public GrupoModel buscar (@PathVariable Long id) {
        return grupoModelAssemble.toModel(cadastroGrupo.buscarOuFalhar(id));
    }

    @PutMapping("/{id}")
    public GrupoModel atualizar (@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long id) {
        var grupoActual = cadastroGrupo.buscarOuFalhar(id);
        grupoInputDesassemble.copyToDomainObject(grupoInput, grupoActual);
        return grupoModelAssemble.toModel(cadastroGrupo.salvar(grupoActual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ecxluir(@PathVariable Long id) {
        cadastroGrupo.excluir(id);
    }

}

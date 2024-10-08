package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.PermissaoAssemble;
import com.algawords.algafoods.api.modelo.PermissaoModel;
import com.algawords.algafoods.domain.modelo.Grupo;
import com.algawords.algafoods.domain.modelo.Permissao;
import com.algawords.algafoods.domain.service.CadastroGrupoService;
import com.algawords.algafoods.domain.service.CadastroPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private CadastroPermissaoService cadastroPermissao;

    @Autowired
    private PermissaoAssemble permissaoAssemble;

    @GetMapping
    public List<PermissaoModel> listar (@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        return permissaoAssemble.toCollectionModel(grupo.getPermossoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar (@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.associacao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar (@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.desassociacao(grupoId, permissaoId);
    }
}

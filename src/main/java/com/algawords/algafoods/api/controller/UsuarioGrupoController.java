package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.GrupoModelAssemble;
import com.algawords.algafoods.api.modelo.GrupoModel;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.service.CadastroGrupoService;
import com.algawords.algafoods.domain.service.CadastroUsuarioService;
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
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private GrupoModelAssemble grupoModelAssemble;

    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        return grupoModelAssemble.toCollectionModel(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar (@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.associarGrupo(grupoId, usuarioId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar (@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.desassociarGrupo(grupoId, usuarioId);
    }
}

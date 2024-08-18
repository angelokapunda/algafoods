package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.UsuarioAssemble;
import com.algawords.algafoods.api.assemble.UsuarioinputDesassemble;
import com.algawords.algafoods.api.modelo.UsuarioModel;
import com.algawords.algafoods.api.modelo.input.SenhaInput;
import com.algawords.algafoods.api.modelo.input.UsuarioInput;
import com.algawords.algafoods.api.modelo.input.UsuarioInputActualizar;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.service.CadastroUsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private UsuarioAssemble usuarioAssemble;

    @Autowired
    private UsuarioinputDesassemble usuarioinputDesassemble;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel cadastrar (@RequestBody @Valid UsuarioInput usuarioInput) {
        var usuario = cadastroUsuario.salvar(usuarioinputDesassemble.toDomainObject(usuarioInput));
        return usuarioAssemble.toModel(usuario);
    }

    @GetMapping
    public List<UsuarioModel> listar () {
        return usuarioAssemble.toCollectionModel(cadastroUsuario.listar());
    }

    @GetMapping("/{id}")
    public UsuarioModel buscar(@PathVariable Long id) {
        return usuarioAssemble.toModel(cadastroUsuario.buscarOuFalhar(id));
    }

    @PutMapping("/{id}")
    public UsuarioModel actualizar (@RequestBody @Valid UsuarioInputActualizar usuarioInputActualizar, @PathVariable Long id) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(id);
        usuarioinputDesassemble.copyToDomainObject(usuarioInputActualizar, usuario);
        return usuarioAssemble.toModel(cadastroUsuario.salvar(usuario));
    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha (@RequestBody @Valid SenhaInput senhaInput, @PathVariable Long id) {
        cadastroUsuario.alterarSenha(id, senhaInput.getSenhaActual(), senhaInput.getNovaSenha());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir ( @PathVariable Long id) {
        cadastroUsuario.excluir(id);
    }











}

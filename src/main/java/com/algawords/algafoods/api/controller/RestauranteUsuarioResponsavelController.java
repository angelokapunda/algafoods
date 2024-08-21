package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.UsuarioAssemble;
import com.algawords.algafoods.api.modelo.UsuarioModel;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.service.CadastroRestauranteService;
import org.apache.catalina.LifecycleState;
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
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private UsuarioAssemble usuarioAssemble;

    @GetMapping
    public List<UsuarioModel> listarResponsaveis(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return usuarioAssemble.toCollectionModel(restaurante.getResponsavel());
    }

    @PutMapping("/{responsavelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarResponsavel (@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
        cadastroRestaurante.adicionarResponsavel(restauranteId, responsavelId);
    }

    @DeleteMapping("/{responsavelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerResponsavel (@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
        cadastroRestaurante.removerResponsavel(restauranteId, responsavelId);
    }
}

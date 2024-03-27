package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algawords.algafoods.domain.modelo.Estado;
import com.algawords.algafoods.domain.service.CadastroEstadosService;
import jakarta.validation.Valid;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadosService cadastroEstado;


    @PostMapping
    public ResponseEntity<Estado> cadastrar (@RequestBody @Valid Estado estado) {
        estado = cadastroEstado.cadastro(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @GetMapping
    public List<Estado> listar() {
        return cadastroEstado.listar();
    }
    @GetMapping("/{id}")
    public Estado buscarEstado(@PathVariable Long id) {
        return cadastroEstado.buscarOuFalgar(id);
    }

    @PutMapping("/{id}")
    public Estado actualizar(@PathVariable Long id, @RequestBody @Valid Estado estado) {
        Estado estadoActual = cadastroEstado.buscarOuFalgar(id);
        BeanUtils.copyProperties(estado, estadoActual, "id");
        return cadastroEstado.cadastro(estadoActual);
    }

    @DeleteMapping("/{id}")
    public void excluir (@PathVariable Long id) {
        cadastroEstado.excluir(id);
    }
}

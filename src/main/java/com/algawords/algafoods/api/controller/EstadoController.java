package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.EstadoAssemble;
import com.algawords.algafoods.api.assemble.EstadoInputDesassemble;
import com.algawords.algafoods.api.modelo.EstadoModel;
import com.algawords.algafoods.api.modelo.input.EstadoIdInput;
import com.algawords.algafoods.api.modelo.input.EstadoInput;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadosService cadastroEstado;

    @Autowired
    private EstadoAssemble estadoAssemble;

    @Autowired
    private EstadoInputDesassemble estadoInputDesassemble;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel cadastrar (@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDesassemble.toModel(estadoInput);
        return estadoAssemble.toModel(cadastroEstado.cadastro(estado));
    }

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoAssemble.lisToModel(cadastroEstado.listar());
    }
    @GetMapping("/{id}")
    public EstadoModel buscarEstado(@PathVariable Long id) {
        return estadoAssemble.toModel(cadastroEstado.buscarOuFalgar(id));
    }

    @PutMapping("/{id}")
    public EstadoModel actualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoActual = cadastroEstado.buscarOuFalgar(id);
        estadoInputDesassemble.copyDomainObject(estadoInput,estadoActual);

//        BeanUtils.copyProperties(estado, estadoActual, "id");
        return estadoAssemble.toModel(cadastroEstado.cadastro(estadoActual));
    }

    @DeleteMapping("/{id}")
    public void excluir (@PathVariable Long id) {
        cadastroEstado.excluir(id);
    }
}

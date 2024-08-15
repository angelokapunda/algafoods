package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.FormaPagamentoInputDesassemble;
import com.algawords.algafoods.api.assemble.FormapagamentoAssemble;
import com.algawords.algafoods.api.modelo.FormaPagamentoModel;
import com.algawords.algafoods.api.modelo.input.FormaPagamentoInput;
import com.algawords.algafoods.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algawords.algafoods.domain.modelo.FormaPagamento;
import com.algawords.algafoods.domain.service.CadastroFormaPagamentoService;
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
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoInputDesassemble formaPagamentoInputDesassemble;

    @Autowired
    private FormapagamentoAssemble formapagamentoAssemble;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel cadastro (@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento =cadastroFormaPagamento.salvar(formaPagamentoInputDesassemble.toDomainObject(formaPagamentoInput));
        return formapagamentoAssemble.toModel(formaPagamento);
    }

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return formapagamentoAssemble.toCollectionModel(cadastroFormaPagamento.lista());
    }

    @GetMapping("/{id}")
    public FormaPagamentoModel buscar (@PathVariable Long id) {
        return formapagamentoAssemble.toModel(cadastroFormaPagamento.buscarFalhar(id));
    }

    @PutMapping("/{id}")
    public FormaPagamentoModel actualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarFalhar(id);
        formaPagamentoInputDesassemble.copyToDomainObject(formaPagamentoInput, formaPagamento);
        return formapagamentoAssemble.toModel(cadastroFormaPagamento.salvar(formaPagamento));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable Long id) {
        cadastroFormaPagamento.excluir(id);
    }
}

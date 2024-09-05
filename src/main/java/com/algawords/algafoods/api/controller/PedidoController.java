package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.PedidoAssemble;
import com.algawords.algafoods.api.assemble.PedidoInputDesassemble;
import com.algawords.algafoods.api.assemble.PedidoResumoAssemble;
import com.algawords.algafoods.api.modelo.PedidoModel;
import com.algawords.algafoods.api.modelo.PedidoResumoModel;
import com.algawords.algafoods.api.modelo.input.PedidoInput;
import com.algawords.algafoods.core.data.PageableTranslator;
import com.algawords.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algawords.algafoods.domain.exception.NegocioException;
import com.algawords.algafoods.domain.modelo.Pedido;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.repository.PedidoRepository;
import com.algawords.algafoods.domain.repository.filter.PedidoFilter;
import com.algawords.algafoods.domain.service.EmissaoPedidoService;
import com.algawords.algafoods.infra.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import jakarta.validation.Valid;
import org.hibernate.metamodel.mapping.internal.ImmutableAttributeMappingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private EmissaoPedidoService cadastroPedido;

    @Autowired
    private PedidoAssemble pedidoAssemble;

    @Autowired
    private PedidoResumoAssemble pedidoResumoAssemble;

    @Autowired
    private PedidoInputDesassemble pedidoInputDesassemble;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(sort = "valorTotal") Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidosResumoModel = pedidoResumoAssemble
                .toCollectionModel(pedidosPage.getContent());

        Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(
                pedidosResumoModel, pageable, pedidosPage.getTotalElements());

        return pedidosResumoModelPage;
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        var pedido = cadastroPedido.buscarOuFalhar(codigoPedido);
        return pedidoAssemble.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDesassemble.toDomainObject(pedidoInput);

            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);
            return pedidoAssemble.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );
        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}

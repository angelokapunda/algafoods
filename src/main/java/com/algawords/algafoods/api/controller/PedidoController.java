package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.PedidoAssemble;
import com.algawords.algafoods.api.assemble.PedidoResumoAssemble;
import com.algawords.algafoods.api.modelo.PedidoModel;
import com.algawords.algafoods.api.modelo.PedidoResumoModel;
import com.algawords.algafoods.domain.modelo.Pedido;
import com.algawords.algafoods.domain.repository.PedidoRepository;
import com.algawords.algafoods.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public List<PedidoResumoModel> listar() {
        var pedidos = cadastroPedido.listar();
        return pedidoResumoAssemble.toCollectionModel(pedidos);
    }


    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        var pedido = cadastroPedido.buscarOuFalhar(pedidoId);
        return pedidoAssemble.toModel(pedido);
    }
}

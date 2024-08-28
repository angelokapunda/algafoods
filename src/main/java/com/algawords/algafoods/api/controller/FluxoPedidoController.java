package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedido;

    @PutMapping("/confirmado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar (@PathVariable Long pedidoId) {
        fluxoPedido.confirmar(pedidoId);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar (@PathVariable Long pedidoId) {
        fluxoPedido.estregue(pedidoId);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar (@PathVariable Long pedidoId) {
        fluxoPedido.cancelar(pedidoId);
    }

}
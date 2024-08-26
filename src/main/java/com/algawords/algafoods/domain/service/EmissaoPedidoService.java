package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.PedidoNaoEncontradoException;
import com.algawords.algafoods.domain.modelo.Pedido;
import com.algawords.algafoods.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido salvar (Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId).orElseThrow(
                () -> new PedidoNaoEncontradoException(pedidoId));
    }

    @Transactional
    public void excluir(Long pedidoId) {
        Pedido pedido = buscarOuFalhar(pedidoId);
        pedidoRepository.delete(pedido);
    }
}

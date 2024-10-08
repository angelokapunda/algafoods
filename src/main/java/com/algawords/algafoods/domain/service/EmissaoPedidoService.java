package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.NegocioException;
import com.algawords.algafoods.domain.exception.PedidoNaoEncontradoException;
import com.algawords.algafoods.domain.modelo.Cidade;
import com.algawords.algafoods.domain.modelo.FormaPagamento;
import com.algawords.algafoods.domain.modelo.Pedido;
import com.algawords.algafoods.domain.modelo.Produto;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private CadastroCidadesService cadastroCidade;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProduto.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    @Transactional
    public Pedido salvar (Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarOuFalhar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido).orElseThrow(
                () -> new PedidoNaoEncontradoException(codigoPedido));
    }

    @Transactional
    public void excluir(String codigoPedido) {
        Pedido pedido = buscarOuFalhar(codigoPedido);
        pedidoRepository.delete(pedido);
    }
}

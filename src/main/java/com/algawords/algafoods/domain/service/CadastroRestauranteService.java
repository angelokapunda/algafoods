package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algawords.algafoods.domain.exception.RestauranteNaoEncontradoException;
import com.algawords.algafoods.domain.modelo.Cidade;
import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.modelo.FormaPagamento;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.repository.CozinhaRepository;
import com.algawords.algafoods.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CadastroCidadesService cadastroCidade;

    @Autowired
    CadastroUsuarioService cadastroUsuario;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);
    }
    @Transactional
    public void remover(Long id){
        try{
            restauranteRepository.deleteById(id);
            restauranteRepository.flush();
        }catch (EntidadeNaoEncontradaException e) {
            e.getMessage();
        }
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteActual = buscarOuFalhar(restauranteId);
        restauranteActual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteActual = buscarOuFalhar(restauranteId);
        restauranteActual.inativar();
    }

    @Transactional
    public void abrirRestaurante (Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abertura();
    }

    @Transactional
    public void fecharRestaurante(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.fechamento();
    }

    @Transactional
    public void desassociacaoFormaPagamento (Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarFalhar(formaPagamentoId);
        restaurante.desassociarFormaPaagamento(formaPagamento);
    }

    @Transactional
    public void associacaoFormaPagamento (Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarFalhar(formaPagamentoId);
        restaurante.associarFormaPaagamento(formaPagamento);
    }

    @Transactional
    public void adicionarResponsavel(Long restauranteId, Long responsavelId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario responsavel = cadastroUsuario.buscarOuFalhar(responsavelId);
        restaurante.associarResponsavel(responsavel);
    }

    @Transactional
    public void removerResponsavel(Long restauranteId, Long responsavelId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario responsavel = cadastroUsuario.buscarOuFalhar(responsavelId);
        restaurante.desassociarResponsavel(responsavel);
    }
}

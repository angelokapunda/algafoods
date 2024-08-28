package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.EntidadeEmUsoException;
import com.algawords.algafoods.domain.exception.EstadoNaoEncontradoException;
import com.algawords.algafoods.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algawords.algafoods.domain.modelo.FormaPagamento;
import com.algawords.algafoods.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroFormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "A forma de pagamento de código %d não pode ser excluida, pós está em uso. ";

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar (FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    public List<FormaPagamento> lista () {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento buscarOuFalhar(Long id) {
        return formaPagamentoRepository.findById(id).orElseThrow(() ->
                new FormaPagamentoNaoEncontradaException(id));
    }

    @Transactional
    public void excluir (Long id) {
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        } catch (EmptyResultDataAccessException e) {
            throw  new EstadoNaoEncontradoException(id);
        }
    }
}

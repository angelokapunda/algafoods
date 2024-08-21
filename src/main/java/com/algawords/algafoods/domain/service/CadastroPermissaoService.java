package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.PermissaoNaoEncontradaExcption;
import com.algawords.algafoods.domain.modelo.Permissao;
import com.algawords.algafoods.domain.modelo.Produto;
import com.algawords.algafoods.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar (Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradaExcption(permissaoId));
    }
}

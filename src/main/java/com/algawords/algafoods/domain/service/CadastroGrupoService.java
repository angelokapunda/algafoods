package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.EntidadeEmUsoException;
import com.algawords.algafoods.domain.exception.EstadoNaoEncontradoException;
import com.algawords.algafoods.domain.exception.GrupoNaoEncontradoException;
import com.algawords.algafoods.domain.modelo.Grupo;
import com.algawords.algafoods.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo não pode ser excluído porque está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscarOuFalhar(Long id) {
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(
                String.format("Grupo de código %d não existe", id)
        ));
    }

    @Transactional
    public void excluir (Long id) {
        Grupo grupo = buscarOuFalhar(id);
        try {
            grupoRepository.delete(grupo);
            grupoRepository.flush();
        }  catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
        } catch (EmptyResultDataAccessException e) {
            throw  new GrupoNaoEncontradoException(String.format("Não existe cadastro degrupo com código %d ", id));
        }
    }
}

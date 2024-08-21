package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.NegocioException;
import com.algawords.algafoods.domain.exception.UsuarioEmUsoException;
import com.algawords.algafoods.domain.exception.UsuarioNaoEncontradoException;
import com.algawords.algafoods.domain.modelo.Grupo;
import com.algawords.algafoods.domain.modelo.Usuario;
import com.algawords.algafoods.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager manager;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        // Para papar de gerenciar o usuario evitando assim uma sicronizacao indesejada
        manager.detach(usuario);

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o email %s", usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public void associarGrupo (Long grupoId, Long usuarioId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        usuario.adicionarGrupos(grupo);
    }

    @Transactional
    public void desassociarGrupo (Long grupoId, Long usuarioId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        usuario.removerGrupos(grupo);
    }

    @Transactional
    public void alterarSenha(Long id, String senhaActual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(id);
        if (usuario.senhaNaoCoincideCom(senhaActual)) {
            throw  new NegocioException("Senha actual informada não conscide com a senha do usuário");
        }
        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void excluir (Long id) {
        var usuario = buscarOuFalhar(id);
        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new UsuarioEmUsoException(String.format("O usuario de código %d esta em uso.", id));
        }
    }
}

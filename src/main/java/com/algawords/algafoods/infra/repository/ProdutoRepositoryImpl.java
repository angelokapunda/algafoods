package com.algawords.algafoods.infra.repository;

import com.algawords.algafoods.domain.modelo.FotoProduto;
import com.algawords.algafoods.domain.repository.ProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }
}

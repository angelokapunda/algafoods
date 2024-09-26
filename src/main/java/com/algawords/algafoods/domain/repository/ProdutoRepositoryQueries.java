package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save (FotoProduto foto);

    void delete(FotoProduto fotp);
}

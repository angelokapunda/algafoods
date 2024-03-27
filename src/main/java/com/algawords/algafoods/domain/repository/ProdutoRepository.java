package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

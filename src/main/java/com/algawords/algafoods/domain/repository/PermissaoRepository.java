package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}

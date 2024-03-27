package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}

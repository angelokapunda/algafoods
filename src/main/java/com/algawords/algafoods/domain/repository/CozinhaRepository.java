package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNome(String nome);

     Cozinha findUnicoByNome(String nome);

     List<Cozinha> findByNomeContaining(String nome);

     boolean existsByNome(String nome);
}

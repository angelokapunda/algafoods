package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}

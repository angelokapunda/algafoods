package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}

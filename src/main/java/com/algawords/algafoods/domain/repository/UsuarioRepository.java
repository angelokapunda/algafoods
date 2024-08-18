package com.algawords.algafoods.domain.repository;

import com.algawords.algafoods.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

package com.algawords.algafoods.domain.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


@Entity
@EqualsAndHashCode(of = "id")
@Data
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"),
    inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> permossoes = new HashSet<>();

    public boolean adicionarPermissao(Permissao permissao) {
        return getPermossoes().add(permissao);
    }
    public boolean removerPermissao (Permissao permissao) {
        return getPermossoes().remove(permissao);
    }
}

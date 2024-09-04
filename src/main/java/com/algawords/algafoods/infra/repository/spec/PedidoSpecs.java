package com.algawords.algafoods.infra.repository.spec;


import com.algawords.algafoods.domain.modelo.Pedido;
import com.algawords.algafoods.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {
            if(Pedido.class.equals(query.getResultType())) {
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();

            if(filtro.getClienteId() != null) {
                predicates.add((java.util.function.Predicate)builder.equal(root.get("cliente"), filtro.getClienteId()));
            }
            if(filtro.getRestauranteId() != null) {
                predicates.add((java.util.function.Predicate)builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }
            if(filtro.getDataCriacaoInicio() != null) {
                predicates.add((java.util.function.Predicate)builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if(filtro.getDataCriacaoFim() != null) {
                predicates.add((java.util.function.Predicate)builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }
}

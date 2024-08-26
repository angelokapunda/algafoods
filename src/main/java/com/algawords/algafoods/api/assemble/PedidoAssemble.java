package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.PedidoModel;
import com.algawords.algafoods.domain.modelo.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream().map(this::toModel)
                .toList();
    }
}

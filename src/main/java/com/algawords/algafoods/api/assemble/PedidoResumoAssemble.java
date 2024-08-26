package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.PedidoModel;
import com.algawords.algafoods.api.modelo.PedidoResumoModel;
import com.algawords.algafoods.domain.modelo.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoResumoAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoModel.class);
    }

    public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream().map(this::toModel)
                .toList();
    }
}

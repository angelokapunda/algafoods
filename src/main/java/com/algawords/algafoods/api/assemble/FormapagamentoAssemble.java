package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.FormaPagamentoModel;
import com.algawords.algafoods.domain.modelo.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormapagamentoAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel (FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel (List<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream().map(formaPagamento -> toModel(formaPagamento))
                .toList();
    }
}

package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.input.EstadoIdInput;
import com.algawords.algafoods.api.modelo.input.EstadoInput;
import com.algawords.algafoods.domain.modelo.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDesassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toModel(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput,Estado.class);
    }

    public void copyDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }
}

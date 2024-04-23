package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.input.CidadeInput;
import com.algawords.algafoods.domain.modelo.Cidade;
import com.algawords.algafoods.domain.modelo.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDesassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyDomainObject(CidadeInput cidadeInput, Cidade cidade) {

        //Para evitar: org.hibernate.HibernateException: identifier of an instance of com.algawords.algafoods.domain.modelo.Cidade
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);
    }
}

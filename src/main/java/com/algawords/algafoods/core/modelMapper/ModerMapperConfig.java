package com.algawords.algafoods.core.modelMapper;

import com.algawords.algafoods.api.modelo.EnderecoModel;
import com.algawords.algafoods.api.modelo.EstadoModel;
import com.algawords.algafoods.domain.modelo.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModerMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoScr -> enderecoScr.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) ->enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }
}

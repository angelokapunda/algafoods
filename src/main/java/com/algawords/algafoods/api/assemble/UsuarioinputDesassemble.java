package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.input.UsuarioInput;
import com.algawords.algafoods.api.modelo.input.UsuarioInputActualizar;
import com.algawords.algafoods.domain.modelo.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioinputDesassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInputActualizar usuarioInputActualizar, Usuario usuario) {
        modelMapper.map(usuarioInputActualizar, usuario);
    }

}

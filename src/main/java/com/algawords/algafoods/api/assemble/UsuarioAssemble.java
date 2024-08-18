package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.UsuarioModel;
import com.algawords.algafoods.domain.modelo.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> toModel(usuario))
                .toList();
    }
}

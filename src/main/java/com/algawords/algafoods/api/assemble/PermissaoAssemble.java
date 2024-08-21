package com.algawords.algafoods.api.assemble;

import com.algawords.algafoods.api.modelo.PermissaoModel;
import com.algawords.algafoods.domain.modelo.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PermissaoAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream().map(permissao -> toModel(permissao))
                .toList();
    }
}

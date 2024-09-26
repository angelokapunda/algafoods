package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.assemble.FotoProdutoModelAssmble;
import com.algawords.algafoods.api.modelo.FotoProdutoModel;
import com.algawords.algafoods.api.modelo.input.FotoProdutoIntput;
import com.algawords.algafoods.domain.modelo.FotoProduto;
import com.algawords.algafoods.domain.modelo.Produto;
import com.algawords.algafoods.domain.service.CadastroProdutoService;
import com.algawords.algafoods.domain.service.CatalogoFotoProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/restaurante/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private FotoProdutoModelAssmble fotoProdutoModelAssmble;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel actualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoIntput fotoProdutoIntput) {

        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoIntput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoIntput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto);
        return fotoProdutoModelAssmble.toModel(fotoSalva);
    }
}

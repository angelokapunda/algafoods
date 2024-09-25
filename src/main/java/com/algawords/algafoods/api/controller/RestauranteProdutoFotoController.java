package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.api.modelo.input.FotoProdutoIntput;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurante/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void actualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoIntput fotoProdutoIntput) {

        var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoIntput.getArquivo().getOriginalFilename();
        var arquivoFoto = Path.of("/home/angelocarlos/Downloads/imagens", nomeArquivo);

        System.out.println(fotoProdutoIntput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoIntput.getArquivo().getContentType());

        try {
            fotoProdutoIntput.getArquivo().transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

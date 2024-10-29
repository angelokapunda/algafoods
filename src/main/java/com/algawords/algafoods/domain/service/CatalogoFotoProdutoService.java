package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.exception.FotoNaoEncontradaException;
import com.algawords.algafoods.domain.modelo.FotoProduto;
import com.algawords.algafoods.domain.modelo.Produto;
import com.algawords.algafoods.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRespository;

    @Autowired
    private FotoStorageService fotoStorage;

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRespository.findFotoById(restauranteId, produtoId).orElseThrow(() ->
                new FotoNaoEncontradaException(restauranteId, produtoId));
    }

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo){
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeArquivoNovo = fotoStorage.gerarNmeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRespository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRespository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeArquivoNovo);
        foto = produtoRespository.save(foto);
        produtoRespository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

        fotoStorage.substituir(nomeArquivoExistente, novaFoto);
        return foto;
    }
}

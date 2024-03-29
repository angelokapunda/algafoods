package com.algawords.algafoods;

import com.algawords.algafoods.domain.exception.CozinhaNaoEncontradaException;
import com.algawords.algafoods.domain.exception.EntidadeEmUsoException;
import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.service.CadastroCozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CadastroCozinhaIntegrationTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirId_QuandoCadatrarCozinhaComDadosCorretos() {

        //Conário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Holandesa");

        //Ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        //Validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();

    }

    @Test
    public void deveFalhar_QuandoCadstrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();

        novaCozinha.setNome(null);

        ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            cadastroCozinha.salvar(novaCozinha);
        });

        assertThat(erroEsperado).isNotNull();

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class, () ->{
            cadastroCozinha.excluir(1L);
        });
        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCozinhaEnexistente() {
        CozinhaNaoEncontradaException erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradaException.class, () ->{
            cadastroCozinha.excluir(10L);
        });
        assertThat(erroEsperado).isNotNull();
    }
}

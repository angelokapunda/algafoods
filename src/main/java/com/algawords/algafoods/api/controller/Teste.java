package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.modelo.Restaurante;
import com.algawords.algafoods.domain.repository.CozinhaRepository;
import com.algawords.algafoods.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class Teste {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/{nome}")
    public List<Cozinha> listar(@PathVariable  String nome)  {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/unico/{nome}")
    public Cozinha lista(@PathVariable  String nome)  {
        System.out.println(nome);
        return cozinhaRepository.findUnicoByNome(nome);
    }

    @GetMapping("/nomeUnico/{nome}")
    public List<Cozinha> list(@PathVariable  String nome)  {
        System.out.println(nome);
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("/taxa")
    public List<Restaurante> buscaComTaxa(BigDecimal inicio, BigDecimal fim) {
        return restauranteRepository.findByTaxaFreteBetween(inicio, fim);
    }

    @GetMapping("/implementacao")
    public List<Restaurante> implemenntacao(String nome,  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome,taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/tudo/{nome}")
    public List<Restaurante> buscaTudo(@PathVariable String nome , Long id) {
        System.out.println(nome + " - " + id);
        return restauranteRepository.consultarPorNome(nome, id);
    }

    @GetMapping("/primeiraVezemVida/{nome}")
    public Optional<Restaurante> buscaPrimeiroNome(@PathVariable  String nome) {
        System.out.println(nome);
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/doisNomes/{nome}")
    public List<Restaurante> buscaDoisNomes(@PathVariable  String nome) {
        System.out.println(nome);
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/exists/{nome}")
    public boolean  verdadeiro(@PathVariable  String nome) {
        System.out.println(nome);
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("/count/{id}")
    public int  nnnverdadeiro(@PathVariable  Long id) {
        System.out.println(id);
        return restauranteRepository.countByCozinhaId(id);
    }


}

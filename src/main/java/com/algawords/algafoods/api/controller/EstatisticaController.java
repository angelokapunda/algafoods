package com.algawords.algafoods.api.controller;

import com.algawords.algafoods.domain.filter.VendasDiariaFilter;
import com.algawords.algafoods.domain.modelo.dto.VendaDiaria;
import com.algawords.algafoods.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendasDiariaFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeoffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeoffset);
    }
}

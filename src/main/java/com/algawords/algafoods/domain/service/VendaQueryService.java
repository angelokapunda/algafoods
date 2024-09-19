package com.algawords.algafoods.domain.service;

import com.algawords.algafoods.domain.filter.VendasDiariaFilter;
import com.algawords.algafoods.domain.modelo.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendasDiariaFilter filtro, String timeoffset);
}

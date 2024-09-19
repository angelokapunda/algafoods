package com.algawords.algafoods.domain.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class VendaDiaria {

    private Date data;
    private Long tolatVendas;
    private BigDecimal totalFaturado;

}

package com.sids.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class PessoaImcMedioFaixaEtariaDto implements Comparable<PessoaImcMedioFaixaEtariaDto> {

    private double imcMedio;
    private String faixaEtaria;


    @Override
    public int compareTo(PessoaImcMedioFaixaEtariaDto pessoaImcMedioFaixaEtariaDto) {
        if (getFaixaEtaria() == null || pessoaImcMedioFaixaEtariaDto.getFaixaEtaria() == null) {
            return 0;
        }
        return getFaixaEtaria().compareTo(pessoaImcMedioFaixaEtariaDto.getFaixaEtaria());
    }

    public void setImcMedio(double imcMedio) {

        BigDecimal bd = BigDecimal.valueOf(imcMedio).setScale(2, RoundingMode.HALF_UP);
        this.imcMedio = bd.doubleValue();
    }
}

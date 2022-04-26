package com.sids.tools;

import com.sids.dtos.PessoaImcDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    PessoaImcDto pessoaImcDto =  new PessoaImcDto("23/05/1964", (float) 1.6,80,"Feminino");


    @Test
    void calculaIdade() {

        assertEquals(57,pessoaImcDto.getIdade());

    }

    @Test
    void calcularFaixaEtaria() {

        assertEquals("50 a 60",pessoaImcDto.getFaixaEtaria());
    }

    @Test
    void calcularImc(){

        assertEquals(31.249998092651367,pessoaImcDto.getImc());
    }
}

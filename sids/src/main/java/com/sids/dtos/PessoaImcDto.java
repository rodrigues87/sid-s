package com.sids.dtos;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Data
@Log4j2
public class PessoaImcDto {
    private String dataNasc;
    private float altura;
    private float peso;

    private double imc;
    private String faixaEtaria;
    private int idade;
    private String pattern = "yyyy-MM-dd";
    private String sexo;
    private boolean obeso;

    public PessoaImcDto(String dataNasc, float altura, float peso, String sexo) {
        this.dataNasc = dataNasc;
        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;

        calculaIdade();
        calcularFaixaEtaria();
        calcularImc();
    }

    public void calculaIdade() {

        DateFormat sdf = new SimpleDateFormat(pattern);

        try {

            Date dataNascInput = sdf.parse(dataNasc);

            Calendar dateOfBirth = new GregorianCalendar();

            dateOfBirth.setTime(dataNascInput);

            Calendar today = Calendar.getInstance();

            int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);


            dateOfBirth.add(Calendar.YEAR, age);

            if (today.before(dateOfBirth)) {

                age--;

            }

            this.idade = age;

        } catch (Exception e) {
            log.error("não foi possivel converter a data: " + e);
        }



    }

    public void calcularFaixaEtaria() {
        int idadeMinima = (idade / 10) * 10;
        int idadeMaxima = idadeMinima + 10;

        this.faixaEtaria =  idadeMinima + " a " + idadeMaxima;
    }

    public void calcularImc() {

        this.imc =  peso / (altura * altura);

        if(this.imc > 30){
            this.obeso = true;
        }
    }
}

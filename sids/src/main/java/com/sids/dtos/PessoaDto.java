package com.sids.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PessoaDto {

    private String nome;
    private String cpf;
    private String rg;

    @JsonProperty(value = "data_nasc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNasc;
    private String sexo;
    private String mae;
    private String pai;
    private String email;
    private String cep;
    private String endereco;
    private float numero;
    private String bairro;
    private String cidade;
    private String estado;
    @JsonProperty(value = "telefone_fixo")
    private String telefoneFixo;
    private String celular;
    private float altura;
    private float peso;
    @JsonProperty(value = "tipo_sanguineo")
    private String tipoSanguineo;
}

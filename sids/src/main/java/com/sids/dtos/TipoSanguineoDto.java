package com.sids.dtos;

public class TipoSanguineoDto   {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
}

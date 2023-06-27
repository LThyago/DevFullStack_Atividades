package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Edificios extends AbstractEntity {

    private String nome;
    private String endereco;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}

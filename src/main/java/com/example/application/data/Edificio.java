package com.example.application.data;

import com.example.application.views.main.ApartamentoForm;

import java.util.ArrayList;
import java.util.List;

public class Edificio {
    private String nome;
    private String endereco;
    List<Apartamento> apartamentos = new ArrayList<>();

    public Edificio(String nome, String endereco){
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void adicionarApartamento(int numero, int andar, double metragem, String situacao){
        Apartamento apartamento = new Apartamento(numero, andar, metragem, situacao);
        apartamentos.add(apartamento);
    }

    public List getApartamentos(){
        return this.apartamentos;
    }

    public Apartamento getLastApartamento(){
        return apartamentos.get(apartamentos.size()-1);
    }
}

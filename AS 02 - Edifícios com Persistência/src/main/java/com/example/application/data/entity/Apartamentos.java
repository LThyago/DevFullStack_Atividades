package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Apartamentos extends AbstractEntity {

    private Integer numero;
    private Integer andar;
    private Integer metragem;
    private String situacao;

    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Integer getAndar() {
        return andar;
    }
    public void setAndar(Integer andar) {
        this.andar = andar;
    }
    public Integer getMetragem() {
        return metragem;
    }
    public void setMetragem(Integer metragem) {
        this.metragem = metragem;
    }
    public String getSituacao() {
        return situacao;
    }
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}

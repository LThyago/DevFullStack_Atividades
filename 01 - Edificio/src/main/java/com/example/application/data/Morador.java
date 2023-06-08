package com.example.application.data;

public class Morador {

    private String nome;
    private String cpf;
    private String telefone;


    public String getNome(){
        return this.nome;
    }

    public String getCpf(){
        return this.cpf;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return """
                Nome do morador: %s
                CPF do morador: %s
                Telefone do morador: %s
                """.formatted(this.getNome(), this.getCpf(), this.getTelefone());
    }

}

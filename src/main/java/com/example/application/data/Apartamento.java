package com.example.application.data;

public class Apartamento {
    private int numero;
    private int andar;
    private double metragem;
    private String situacao;
    private Morador morador = new Morador();

    public Apartamento(int numero, int andar, double metragem, String situacao){
        this.numero = numero;
        this.andar = andar;
        this.metragem = metragem;
        this.situacao = situacao;
    }

    public int getNumero(){
        return this.numero;
    }

    public int getAndar(){
        return this.andar;
    }

    public double getMetragem(){
        return this.metragem;
    }

    public String getSituacao(){
        return this.situacao;
    }

    public Morador getMorador(){
        return this.morador;
    }

    public void cadastrarMorador(String nome, String cpf, String telefone){
        this.morador.setNome(nome);
        this.morador.setCpf(cpf);
        this.morador.setTelefone(telefone);
    }

    @Override
    public String toString(){
        return """
                Número do Apartamento: %d
                Andar do Apartamento: %d
                Metragem do Apartamento: %.2f
                Situação do Apartamento: %s
                Morador do Apartamento: %s 
                
                """.formatted(this.getNumero(), this.getAndar(), this.getMetragem(), this.getSituacao(), this.getMorador().getNome());
    }
}

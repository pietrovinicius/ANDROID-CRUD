package com.br.listadecompras.Model;

public class Produto {

    private long id;
    private String descricao;
    private double qnt;
    private double valor;




    public Produto(){
        descricao = "";
        qnt = 00;
        valor = 00;
    }

    //método contrutor
    public Produto(String descricao, String quantidade, String valor) {
        this.descricao = descricao;
        this.qnt = Double.parseDouble(quantidade);
        this.valor = Double.parseDouble(valor);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getQnt() {
        return qnt;
    }

    public void setQnt(double qnt) {
        this.qnt = qnt;
    }

    @Override
    public String toString() {
        return  descricao +
                " - " + qnt + "x" +
                " - R$" + valor;
    }
}

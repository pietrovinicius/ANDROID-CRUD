package com.br.listadecompras.Model;

public class Produto {

    private long id;
    private String descricao;
    private double valor;
    private double qnt;

    //método contrutor
    public Produto(long id, String descricao, float valor, float qnt) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.qnt = qnt;
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
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", qnt=" + qnt +
                '}';
    }
}

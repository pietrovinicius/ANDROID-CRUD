package com.br.listadecompras.Model;

public class Produto {

    private long id;
    private String descricao;
    private String qnt;
    private String valor;




    public Produto(){
        descricao = "";
        qnt = "";
        valor = "";
    }

    //método contrutor
    public Produto(String descricao, String quantidade, String valor) {
        this.descricao = descricao;
        this.qnt = quantidade;
        this.valor = valor;
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

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt = qnt;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  descricao +
                " - " + qnt + "x" +
                " - R$" + valor;
    }
}

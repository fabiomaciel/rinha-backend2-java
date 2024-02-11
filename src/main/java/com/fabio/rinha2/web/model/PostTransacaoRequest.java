package com.fabio.rinha2.web.model;

import java.math.BigInteger;

public class PostTransacaoRequest {
    private BigInteger valor;
    private Character tipo;
    private String descricao;

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

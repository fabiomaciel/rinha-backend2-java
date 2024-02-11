package com.fabio.rinha2.web.model;

import java.math.BigInteger;

public class PostTransacaoResponse {
    private BigInteger limite;
    private BigInteger saldo;

    public BigInteger getLimite() {
        return limite;
    }

    public void setLimite(BigInteger limite) {
        this.limite = limite;
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        this.saldo = saldo;
    }
}

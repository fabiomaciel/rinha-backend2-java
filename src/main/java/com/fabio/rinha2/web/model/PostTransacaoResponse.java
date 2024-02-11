package com.fabio.rinha2.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;

public class PostTransacaoResponse {
    private BigInteger limite;
    private BigInteger saldo;

    @JsonIgnore
    private boolean sufficientBalance;

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

    public boolean isSufficientBalance() {
        return sufficientBalance;
    }

    public void setSufficientBalance(boolean sufficientBalance) {
        this.sufficientBalance = sufficientBalance;
    }
}

package com.fabio.rinha2.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;

public class PostTransacaoResponse {
    private BigInteger limite;
    private BigInteger saldo;

    @JsonIgnore
    private ErrorType error;


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

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }

    public static enum ErrorType {
        INSUFFICIENT_BALANCE,
        INVALID_ACCOUNT
    }

    public static PostTransacaoResponseBuilder builder() {
        return new PostTransacaoResponseBuilder();
    }

    public static class PostTransacaoResponseBuilder {
        private BigInteger limite;
        private BigInteger saldo;
        private ErrorType error;

        PostTransacaoResponseBuilder() {
        }

        public PostTransacaoResponseBuilder limite(BigInteger limite) {
            this.limite = limite;
            return this;
        }

        public PostTransacaoResponseBuilder saldo(BigInteger saldo) {
            this.saldo = saldo;
            return this;
        }

        public PostTransacaoResponseBuilder error(ErrorType error) {
            this.error = error;
            return this;
        }

        public PostTransacaoResponse build() {
            PostTransacaoResponse postTransacaoResponse = new PostTransacaoResponse();
            postTransacaoResponse.setLimite(limite);
            postTransacaoResponse.setSaldo(saldo);
            postTransacaoResponse.setError(error);
            return postTransacaoResponse;
        }

        public String toString() {
            return "PostTransacaoResponse.PostTransacaoResponseBuilder(limite=" + this.limite + ", saldo=" + this.saldo + ", error=" + this.error + ")";
        }
    }
}

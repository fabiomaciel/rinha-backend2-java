package com.fabio.rinha2.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class GetExtratoResponse {
    @JsonProperty("data_extrato")
    private LocalDateTime dataExtrato;
    private BigInteger limite;
    private BigInteger total;

    @JsonProperty("ultimas_transacoes")
    private List<Object> transacoes;

    public GetExtratoResponse() {
    }

    public GetExtratoResponse(BigInteger limite, BigInteger total) {
        this.limite = limite;
        this.total = total;
        this.dataExtrato = LocalDateTime.now();
        this.transacoes = List.of();
    }



    public LocalDateTime getDataExtrato() {
        return dataExtrato;
    }

    public void setDataExtrato(LocalDateTime dataExtrato) {
        this.dataExtrato = dataExtrato;
    }

    public BigInteger getLimite() {
        return limite;
    }

    public void setLimite(BigInteger limite) {
        this.limite = limite;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public List<Object> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Object> transacoes) {
        this.transacoes = transacoes;
    }
}

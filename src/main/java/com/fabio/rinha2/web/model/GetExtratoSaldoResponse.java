package com.fabio.rinha2.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class GetExtratoSaldoResponse {
    @JsonProperty("data_extrato")
    private LocalDateTime dataExtrato;
    private BigInteger limite;
    private BigInteger total;

    public GetExtratoSaldoResponse() {
    }

    public GetExtratoSaldoResponse(BigInteger limite, BigInteger total) {
        this.limite = limite;
        this.total = total;
        this.dataExtrato = LocalDateTime.now();
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

}

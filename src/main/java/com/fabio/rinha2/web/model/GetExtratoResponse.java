package com.fabio.rinha2.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetExtratoResponse {

    private GetExtratoSaldoResponse saldo;
    @JsonProperty("ultimas_transacoes")
    private List<GetExtratoTransacaoResponse> transacoes;

    public GetExtratoSaldoResponse getSaldo() {
        return saldo;
    }

    public void setSaldo(GetExtratoSaldoResponse saldo) {
        this.saldo = saldo;
    }

    public List<GetExtratoTransacaoResponse> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<GetExtratoTransacaoResponse> transacoes) {
        this.transacoes = transacoes;
    }
}

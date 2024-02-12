package com.fabio.rinha2.web.model;

import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class GetExtratoTransacaoResponse {

    private BigInteger valor;
    private Character tipo;
    private String descricao;

    @JsonProperty("realizada_em")
    private LocalDateTime realizadaEm;

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

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(LocalDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }

    public static GetExtratoTransacaoResponse fromMovimentacao(MovimentacaoEntity movimentacao){
        GetExtratoTransacaoResponse response = new GetExtratoTransacaoResponse();
        response.setValor(movimentacao.getValor());
        response.setTipo(movimentacao.getTipo());
        response.setDescricao(Optional.ofNullable(movimentacao.getDescricao()).map(String::trim).orElse(""));
        response.setRealizadaEm(movimentacao.getDataMovimentacao());
        return response;
    }

    public static List<GetExtratoTransacaoResponse> fromMovimentacoes(List<MovimentacaoEntity> movimentacoes){
        return movimentacoes.stream().map(GetExtratoTransacaoResponse::fromMovimentacao).toList();
    }
}

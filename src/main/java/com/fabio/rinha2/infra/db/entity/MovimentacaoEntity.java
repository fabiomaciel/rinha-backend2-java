package com.fabio.rinha2.infra.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimentacaoEntity {

    private Integer id;
    private Integer idCliente;
    private BigInteger valor;
    private Character tipo;
    private String descricao;
    private LocalDateTime dataMovimentacao;

    private BigInteger limite;

    private BigInteger saldo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

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

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

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

    public static MovimentacaoEntityBuilder builder() {
        return new MovimentacaoEntityBuilder();
    }

    public static class MovimentacaoEntityBuilder {
        private Integer id;
        private Integer idCliente;
        private BigInteger valor;
        private Character tipo;
        private String descricao;
        private LocalDateTime dataMovimentacao;

        private BigInteger limite;

        private BigInteger saldo;

        MovimentacaoEntityBuilder() {
        }

        public MovimentacaoEntityBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public MovimentacaoEntityBuilder idCliente(Integer idCliente) {
            this.idCliente = idCliente;
            return this;
        }

        public MovimentacaoEntityBuilder valor(BigInteger valor) {
            this.valor = valor;
            return this;
        }

        public MovimentacaoEntityBuilder tipo(Character tipo) {
            this.tipo = tipo;
            return this;
        }

        public MovimentacaoEntityBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public MovimentacaoEntityBuilder dataMovimentacao(LocalDateTime dataMovimentacao) {
            this.dataMovimentacao = dataMovimentacao;
            return this;
        }

        public MovimentacaoEntityBuilder limite(BigInteger limite) {
            this.limite = limite;
            return this;
        }

        public MovimentacaoEntityBuilder saldo(BigInteger saldo) {
            this.saldo = saldo;
            return this;
        }

        public MovimentacaoEntity build() {
            MovimentacaoEntity movimentacaoEntity = new MovimentacaoEntity();
            movimentacaoEntity.setId(id);
            movimentacaoEntity.setIdCliente(idCliente);
            movimentacaoEntity.setValor(valor);
            movimentacaoEntity.setTipo(tipo);
            movimentacaoEntity.setDescricao(descricao);
            movimentacaoEntity.setDataMovimentacao(dataMovimentacao);
            movimentacaoEntity.setLimite(limite);
            movimentacaoEntity.setSaldo(saldo);
            return movimentacaoEntity;
        }
    }
}

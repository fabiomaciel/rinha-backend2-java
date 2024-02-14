package com.fabio.rinha2.infra.db.entity;

import java.math.BigInteger;

public class ClienteEntity {

    private Integer id;
    private BigInteger limite;
    private BigInteger saldo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public static ClienteEntityBuilder builder() {
        return new ClienteEntityBuilder();
    }

    public static class ClienteEntityBuilder {
        private Integer id;
        private BigInteger limite;
        private BigInteger saldo;

        ClienteEntityBuilder() {
        }

        public ClienteEntityBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ClienteEntityBuilder limite(BigInteger limite) {
            this.limite = limite;
            return this;
        }

        public ClienteEntityBuilder saldo(BigInteger saldo) {
            this.saldo = saldo;
            return this;
        }

        public ClienteEntity build() {
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setId(id);
            clienteEntity.setLimite(limite);
            clienteEntity.setSaldo(saldo);
            return clienteEntity;
        }
    }
}

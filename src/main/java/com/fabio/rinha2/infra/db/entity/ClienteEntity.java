package com.fabio.rinha2.infra.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigInteger;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "TB_CLIENTE")
public class ClienteEntity {

    @Id
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
}

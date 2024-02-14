package com.fabio.rinha2.infra.db.repository;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class ClienteRepositoryR {

    private final DatabaseClient databaseClient;

    public ClienteRepositoryR(R2dbcEntityTemplate template) {
        this.databaseClient = template.getDatabaseClient();
    }

    public Mono<ClienteEntity> findById(Integer id) {
        return databaseClient.sql("SELECT * FROM TB_CLIENTE WHERE id = :id")
                .bind("id", id)
                .map((row, rowMetadata) -> ClienteEntity.builder()
                        .id(row.get("id", Integer.class))
                        .limite(row.get("limite", BigInteger.class))
                        .saldo(row.get("saldo", BigInteger.class))
                        .build())
                .one();
    }

    @Transactional
    public Mono<Long> credito(Integer id, BigInteger amount) {
        String query = "UPDATE TB_CLIENTE SET saldo = saldo + :amount WHERE id = :id";
        return databaseClient
                .sql(query)
                .bind("id", id)
                .bind("amount", amount)
                .fetch()
                .rowsUpdated();
    }

    @Transactional
    public Mono<Long> debito(Integer id, BigInteger amount) {
        String query = "UPDATE TB_CLIENTE SET saldo = saldo - :amount WHERE id = :id and saldo - :amount > limite * -1";
        return databaseClient
                .sql(query)
                .bind("id", id)
                .bind("amount", amount)
                .fetch()
                .rowsUpdated();
    }

    public Mono<ClienteEntity> findSaldoByIdNative(Integer id) {
        String query = "SELECT id, saldo, limite FROM TB_CLIENTE WHERE id = :id";
        return databaseClient
                .sql(query)
                .bind("id", id)
                .map((row, rowMetadata) -> ClienteEntity.builder()
                        .id(row.get("id", Integer.class))
                        .saldo(row.get("saldo", BigInteger.class))
                        .limite(row.get("limite", BigInteger.class))
                        .build())
                .one();
    }
}

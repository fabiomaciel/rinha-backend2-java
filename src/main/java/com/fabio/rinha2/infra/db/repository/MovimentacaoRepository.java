package com.fabio.rinha2.infra.db.repository;

import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MovimentacaoRepository {

    private final DatabaseClient databaseClient;

    public MovimentacaoRepository(R2dbcEntityTemplate template) {
        this.databaseClient = template.getDatabaseClient();
    }

    public void save(MovimentacaoEntity movimentacao) {
        String query = "INSERT INTO tb_movimentacao (id_cliente, descricao, valor, tipo, data_movimentacao) VALUES (:idCliente, :descricao, :valor, :tipo, :dataMovimentacao)";
        databaseClient
                .sql(query)
                .bind("idCliente", movimentacao.getIdCliente())
                .bind("descricao", movimentacao.getDescricao())
                .bind("valor", movimentacao.getValor())
                .bind("tipo", movimentacao.getTipo())
                .bind("dataMovimentacao", movimentacao.getDataMovimentacao())
                .fetch().one().subscribe();
    }

    public Mono<List<MovimentacaoEntity>> findTop10ByClienteId(Integer id) {
        //String query2 = "SELECT * FROM tb_movimentacao WHERE id_cliente = :id ORDER BY data_movimentacao DESC LIMIT 10";

        String query = "SELECT tb_cliente.*, tb_movimentacao.* FROM tb_cliente " +
                "    LEFT JOIN tb_movimentacao on tb_cliente.id = tb_movimentacao.id_cliente " +
                "    WHERE tb_cliente.id = :id ORDER BY data_movimentacao DESC LIMIT 10";

        return databaseClient
                .sql(query)
                .bind("id", id)
                .map((row, rowMetadata) -> MovimentacaoEntity.builder()
                        .id(row.get("id", Integer.class))
                        .idCliente(row.get("id_cliente", Integer.class))
                        .descricao(row.get("descricao", String.class))
                        .valor(row.get("valor", BigInteger.class))
                        .tipo(row.get("tipo", Character.class))
                        .dataMovimentacao(row.get("data_movimentacao", LocalDateTime.class))
                        .limite(row.get("limite", BigInteger.class))
                        .saldo(row.get("saldo", BigInteger.class))
                        .build())
                .all()
                .collectList();
    }
}

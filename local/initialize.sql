CREATE TABLE IF NOT EXISTS TB_CLIENTE(
    id smallint PRIMARY KEY,
    limite bigint NOT NULL default 0,
    saldo bigint NOT NULL default 0
);

CREATE TABLE IF NOT EXISTS TB_MOVIMENTACAO(
    id serial PRIMARY KEY NOT NULL,
    id_cliente smallint NOT NULL,
    valor bigint NOT NULL,
    tipo character(1) NOT NULL,
    descricao character(10) NOT NULL,
    data_movimentacao timestamp default current_timestamp,
    FOREIGN KEY (id_cliente) REFERENCES TB_CLIENTE(id)
);


INSERT INTO TB_CLIENTE(id, limite, saldo) VALUES (1, 100000, 0);
INSERT INTO TB_CLIENTE(id, limite, saldo) VALUES (2, 80000, 0);
INSERT INTO TB_CLIENTE(id, limite, saldo) VALUES (3, 1000000, 0);
INSERT INTO TB_CLIENTE(id, limite, saldo) VALUES (4, 10000000, 0);
INSERT INTO TB_CLIENTE(id, limite, saldo) VALUES (5, 500000, 0);

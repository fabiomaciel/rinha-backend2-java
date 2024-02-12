truncate table tb_movimentacao;

update tb_cliente set saldo = 0 WHERE id > 0;
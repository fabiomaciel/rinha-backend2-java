package com.fabio.rinha2.infra.db.repository;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Modifying
    @Query(value = "UPDATE TB_CLIENTE SET saldo = saldo + :amount WHERE id = :id", nativeQuery = true)
    int credito(Integer id, BigInteger amount);

    @Modifying
    @Query(value = "UPDATE TB_CLIENTE SET saldo = saldo - :amount WHERE id = :id and saldo - :amount > limite * -1", nativeQuery = true)
    int debito(Integer id, BigInteger amount);

    @Query(value = "SELECT id, saldo, limite FROM TB_CLIENTE WHERE id = :id", nativeQuery = true)
    Optional<ClienteEntity> findSaldoById(Integer id);


}

package com.fabio.rinha2.infra.db.repository;

import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Integer>{

    @Query(value = "SELECT * FROM tb_movimentacao WHERE id_cliente = :id ORDER BY data_movimentacao DESC LIMIT 10", nativeQuery = true)    List<MovimentacaoEntity> findTop10ByClienteId(Integer id);
}

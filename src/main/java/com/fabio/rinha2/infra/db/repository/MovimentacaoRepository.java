package com.fabio.rinha2.infra.db.repository;

import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Integer>{

}

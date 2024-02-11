package com.fabio.rinha2.infra.db.repository;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{

}

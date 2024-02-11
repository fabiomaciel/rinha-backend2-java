package com.fabio.rinha2.web.model.mapper;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import org.springframework.stereotype.Component;

@Component
public class ExtratoMapper {

    public GetExtratoResponse fromClient(ClienteEntity cliente){
        return new GetExtratoResponse(cliente.getLimite(), cliente.getSaldo());
    }
}

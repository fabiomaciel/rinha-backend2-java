package com.fabio.rinha2.web.model.mapper;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import com.fabio.rinha2.web.model.GetExtratoSaldoResponse;
import com.fabio.rinha2.web.model.GetExtratoTransacaoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExtratoMapper {

    public GetExtratoResponse fromClient(ClienteEntity cliente, List<MovimentacaoEntity> movimentacaoList) {
        GetExtratoResponse response = new GetExtratoResponse();
        response.setSaldo(new GetExtratoSaldoResponse(cliente.getLimite(), cliente.getSaldo()));
        response.setTransacoes(GetExtratoTransacaoResponse.fromMovimentacoes(movimentacaoList));
        return response;
    }
}
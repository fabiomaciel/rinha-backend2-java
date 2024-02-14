package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepositoryR;
import com.fabio.rinha2.infra.db.repository.MovimentacaoRepository;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import com.fabio.rinha2.web.model.mapper.ExtratoMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExtratoService {

    private final ClienteRepositoryR clienteRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final ExtratoMapper extratoMapper;

    public ExtratoService(ClienteRepositoryR clienteRepository, MovimentacaoRepository movimentacaoRepository, ExtratoMapper extratoMapper) {
        this.clienteRepository = clienteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.extratoMapper = extratoMapper;
    }

    public Mono<GetExtratoResponse> getExtrato(Integer id) {
        return movimentacaoRepository.findTop10ByClienteId(id)
                .map(extratoMapper::fromMovimentacao);
    }

    public Mono<ClienteEntity> getClient(Integer id) {
        return clienteRepository
                .findSaldoByIdNative(id);
    }
}

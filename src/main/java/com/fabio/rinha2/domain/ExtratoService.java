package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.repository.ClienteRepository;
import com.fabio.rinha2.infra.db.repository.MovimentacaoRepository;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import com.fabio.rinha2.web.model.mapper.ExtratoMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExtratoService {

    private final ClienteRepository clienteRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final ExtratoMapper extratoMapper;

    public ExtratoService(ClienteRepository clienteRepository, MovimentacaoRepository movimentacaoRepository, ExtratoMapper extratoMapper) {
        this.clienteRepository = clienteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.extratoMapper = extratoMapper;
    }

    public Optional<GetExtratoResponse> getExtrato(Integer id) {
        return clienteRepository
                .findSaldoByIdNative(id)
                .map(clienteEntity ->
                        extratoMapper.fromClient(clienteEntity, movimentacaoRepository.findTop10ByClienteId(id))
                );
    }
}

package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.repository.ClienteRepository;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import com.fabio.rinha2.web.model.mapper.ExtratoMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExtratoService {

    private final ClienteRepository clienteRepository;
    private final ExtratoMapper extratoMapper;

    public ExtratoService(ClienteRepository clienteRepository, ExtratoMapper extratoMapper) {
        this.clienteRepository = clienteRepository;
        this.extratoMapper = extratoMapper;
    }

    public Optional<GetExtratoResponse> getExtrato(Integer id) {
        return clienteRepository.findSaldoById(id).map(extratoMapper::fromClient);
    }
}

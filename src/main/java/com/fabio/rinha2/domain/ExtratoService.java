package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepository;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import org.springframework.stereotype.Service;

@Service
public class ExtratoService {

    private final ClienteRepository clienteRepository;
    private final ExtratoMapper extratoMapper;

    public ExtratoService(ClienteRepository clienteRepository, ExtratoMapper extratoMapper) {
        this.clienteRepository = clienteRepository;
        this.extratoMapper = extratoMapper;
    }

    public GetExtratoResponse getExtrato(Integer id){
        ClienteEntity cliente = clienteRepository.getReferenceById(id);
        return extratoMapper.fromClient(cliente);
    }
}

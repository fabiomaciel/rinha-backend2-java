package com.fabio.rinha2.web;

import com.fabio.rinha2.domain.ExtratoService;
import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepository;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes/{id}")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ExtratoService extratoService;

    public ClienteController(ClienteRepository clienteRepository, ExtratoService extratoService) {
        this.clienteRepository = clienteRepository;
        this.extratoService = extratoService;
    }

    @GetMapping
    public ClienteEntity getCliente(@PathVariable Integer id) {
        return clienteRepository.getReferenceById(id);
    }

    @GetMapping("/extrato")
    public GetExtratoResponse getExtrato(@PathVariable Integer id) {
        return extratoService.getExtrato(id);
    }
}

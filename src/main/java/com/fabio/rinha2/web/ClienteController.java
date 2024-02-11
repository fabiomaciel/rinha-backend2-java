package com.fabio.rinha2.web;

import com.fabio.rinha2.domain.ExtratoService;
import com.fabio.rinha2.domain.TransacaoService;
import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepository;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import com.fabio.rinha2.web.model.PostTransacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/{id}")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ExtratoService extratoService;

    private final TransacaoService transacaoService;

    public ClienteController(ClienteRepository clienteRepository, ExtratoService extratoService, TransacaoService transacaoService) {
        this.clienteRepository = clienteRepository;
        this.extratoService = extratoService;
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ClienteEntity getCliente(@PathVariable Integer id) {
        return clienteRepository.getReferenceById(id);
    }

    @GetMapping("/extrato")
    public ResponseEntity<GetExtratoResponse> getExtrato(@PathVariable Integer id) {
        return extratoService.getExtrato(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/transacoes")
    public ResponseEntity<?> postTransacao(@PathVariable Integer id, @RequestBody PostTransacaoRequest request) {
        return transacaoService.executarMovimentacao(id, request)
                .map(transacaoResponse -> {
                    if (transacaoResponse.isSufficientBalance()) {
                        return ResponseEntity.ok(transacaoResponse);
                    } else {
                        return ResponseEntity.status(422).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

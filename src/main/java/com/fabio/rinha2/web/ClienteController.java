package com.fabio.rinha2.web;

import com.fabio.rinha2.domain.ExtratoService;
import com.fabio.rinha2.domain.TransacaoService;
import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepositoryR;
import com.fabio.rinha2.web.model.GetExtratoResponse;
import com.fabio.rinha2.web.model.PostTransacaoRequest;
import com.fabio.rinha2.web.model.PostTransacaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/clientes/{id}")
public class ClienteController {

    private final ClienteRepositoryR clienteRepository;
    private final ExtratoService extratoService;

    private final TransacaoService transacaoService;

    public ClienteController(ClienteRepositoryR clienteRepository, ExtratoService extratoService, TransacaoService transacaoService) {
        this.clienteRepository = clienteRepository;
        this.extratoService = extratoService;
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public Mono<ResponseEntity<ClienteEntity>> getCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/extrato")
    public Mono<ResponseEntity<GetExtratoResponse>> getExtrato(@PathVariable Integer id) {
        if (id > 5 || id < 1) return Mono.just(ResponseEntity.notFound().build());

        return extratoService.getExtrato(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/transacoes")
    public Mono<ResponseEntity<PostTransacaoResponse>> postTransacao(@PathVariable Integer id, @RequestBody PostTransacaoRequest request) {
        if (id > 5 || id < 1) return Mono.just(ResponseEntity.notFound().build());

        if (isInvalidRequest(request)) return status(422);

        return transacaoService.executarMovimentacao(id, request)
                .map(transacaoResponse -> {
                    if (transacaoResponse.getError() == null) {
                        return ResponseEntity.ok().body(transacaoResponse);
                    } else {
                        return ResponseEntity.status(422).build();
                    }
                });
    }

    private boolean isInvalidRequest(PostTransacaoRequest request) {
        return isInvalidDescription(request) || isInvalidValue(request.getValor()) || isInvalidType(request);
    }

    private boolean isInvalidType(PostTransacaoRequest request) {
        return request.getTipo() == null || (request.getTipo() != 'c' && request.getTipo() != 'd');
    }

    private boolean isInvalidDescription(PostTransacaoRequest request) {
        return request.getDescricao() == null || request.getDescricao().isBlank() || request.getDescricao().length() > 10;
    }

    public boolean isInvalidValue(BigDecimal valor) {
        return valor == null || valor.compareTo(BigDecimal.ZERO) <= 0 || !valor.subtract(valor.abs()).equals(BigDecimal.ZERO);
    }

    public static Mono<ResponseEntity<PostTransacaoResponse>> status(int statusCode) {
        return Mono.just(ResponseEntity.status(statusCode).build());
    }
}

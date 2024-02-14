package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepositoryR;
import com.fabio.rinha2.infra.db.repository.MovimentacaoRepository;
import com.fabio.rinha2.web.model.PostTransacaoRequest;
import com.fabio.rinha2.web.model.PostTransacaoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static com.fabio.rinha2.web.model.PostTransacaoResponse.ErrorType.INSUFFICIENT_BALANCE;
import static com.fabio.rinha2.web.model.PostTransacaoResponse.ErrorType.INVALID_ACCOUNT;

@Service
public class TransacaoService {

    private final ClienteRepositoryR clienteRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    private final MovimentacaoServiceAsync movimentacaoServiceAsync;

    public TransacaoService(ClienteRepositoryR clienteRepository, MovimentacaoRepository movimentacaoRepository, MovimentacaoServiceAsync movimentacaoServiceAsync) {
        this.clienteRepository = clienteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoServiceAsync = movimentacaoServiceAsync;
    }


    public Mono<PostTransacaoResponse> executarMovimentacao(Integer id, PostTransacaoRequest request) {

        BigInteger valor = request.getValor().toBigInteger();

        return doCrebito(id, request, valor).flatMap((success) -> {
            if (success == 0) {
                return Mono.just(PostTransacaoResponse.builder().error(INSUFFICIENT_BALANCE).build());
            }
            return getClienteIfExists(id).map(cliente -> {
                saveMovimentacao(id, request, valor);
                return cliente;
            });
        });

    }


    private Mono<PostTransacaoResponse> getClienteIfExists(Integer id) {
        return clienteRepository.findSaldoByIdNative(id).map(cliente -> {
            if (cliente == null) {
                return PostTransacaoResponse.builder().error(INVALID_ACCOUNT).build();
            }
            return PostTransacaoResponse.builder().limite(cliente.getLimite()).saldo(cliente.getSaldo()).build();
        });
    }

    private Mono<Long> doCrebito(Integer id, PostTransacaoRequest request, BigInteger valor) {
        return executeCrebito(id, request, valor);
    }

    private Mono<Long> executeCrebito(Integer id, PostTransacaoRequest request, BigInteger valor) {
        if (request.getTipo().equals('c')) {
            return clienteRepository.credito(id, valor);
        }
        return clienteRepository.debito(id, valor);
    }

    private void saveMovimentacao(Integer id, PostTransacaoRequest request, BigInteger valor) {
        MovimentacaoEntity movimentacao = new MovimentacaoEntity();
        movimentacao.setIdCliente(id);
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setValor(valor);
        movimentacao.setTipo(request.getTipo());
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacaoServiceAsync.save(movimentacao);
    }
}

package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.entity.ClienteEntity;
import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import com.fabio.rinha2.infra.db.repository.ClienteRepository;
import com.fabio.rinha2.infra.db.repository.ClienteRepositoryR;
import com.fabio.rinha2.infra.db.repository.MovimentacaoRepository;
import com.fabio.rinha2.web.model.PostTransacaoRequest;
import com.fabio.rinha2.web.model.PostTransacaoResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransacaoService {

    private final ClienteRepository clienteRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public TransacaoService(ClienteRepository clienteRepository, MovimentacaoRepository movimentacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional
    public Optional<PostTransacaoResponse> executarMovimentacao(Integer id, PostTransacaoRequest request) {

        BigInteger valor = request.getValor().toBigInteger();

        int success = 1;
        if (request.getTipo().equals('c')) {
            clienteRepository.credito(id, valor);
        } else {
            success = clienteRepository.debito(id, valor);
        }

        Optional<ClienteEntity> cliente = clienteRepository.findSaldoByIdNative(id);

        if (cliente.isEmpty()) {
            return Optional.empty();
        }

        if (success == 0) {
            PostTransacaoResponse response = new PostTransacaoResponse();
            response.setSufficientBalance(false);
            return Optional.of(response);
        }

        final MovimentacaoEntity movimentacao = new MovimentacaoEntity();
        movimentacao.setIdCliente(id);
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setValor(valor);
        movimentacao.setTipo(request.getTipo());
        movimentacao.setDataMovimentacao(LocalDateTime.now());

        movimentacaoRepository.save(movimentacao);

        PostTransacaoResponse response = new PostTransacaoResponse();
        response.setSufficientBalance(true);

        return cliente.map(c -> {
            response.setLimite(c.getLimite());
            response.setSaldo(c.getSaldo());
            return response;
        });
    }
}

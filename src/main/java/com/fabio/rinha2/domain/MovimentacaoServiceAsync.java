package com.fabio.rinha2.domain;

import com.fabio.rinha2.infra.db.entity.MovimentacaoEntity;
import com.fabio.rinha2.infra.db.repository.MovimentacaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Service
public class MovimentacaoServiceAsync {

    private final ExecutorService executor = newFixedThreadPool(1);
    private final BlockingQueue<MovimentacaoEntity> queue;

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoServiceAsync(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        queue = new LinkedBlockingQueue<>();
    }

    @PostConstruct
    public void run() {
        executor.execute(() -> {
            while (true) {
                try {
                    MovimentacaoEntity id = queue.take();
                    movimentacaoRepository.save(id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void save(MovimentacaoEntity movimentacao) {
        //nothing to do
        //queue.add(movimentacao);
    }


}

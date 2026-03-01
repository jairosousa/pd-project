package com.jnsdev.paradigmareativa.service;


import com.jnsdev.paradigmareativa.strategy.PagamentoStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 16:56
 */
@Service
public class VendaService {

    private final List<PagamentoStrategy> estrategias;

    public VendaService(List<PagamentoStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public Mono<Void> finalizarVenda(String metodo, Double valor) {
        return Flux.fromIterable(estrategias)
                .filter(s -> s.aplica(metodo))
                .next() // Pega o primeiro que encontrar (retorna um Mono)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Método inválido")))
                .flatMap(s -> s.pagar(valor)); // Executa a estratégia
    }
}

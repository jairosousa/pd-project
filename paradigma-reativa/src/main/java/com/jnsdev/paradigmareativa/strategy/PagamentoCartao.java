package com.jnsdev.paradigmareativa.strategy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 16:55
 */
@Component
public class PagamentoCartao implements PagamentoStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoCartao.class);

    @Override
    public Mono<Void> pagar(Double valor) {
        // Exemplo: simulando um delay de rede sem travar a thread
        return Mono.delay(Duration.ofMillis(500))
                .doOnNext(v -> LOGGER.info("Cartão processado de forma assíncrona R$ {}", valor))
                .then();
    }

    @Override
    public boolean aplica(String metodo) {
        return "CARTAO".equalsIgnoreCase(metodo);
    }
}

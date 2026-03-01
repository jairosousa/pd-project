package com.jnsdev.paradigmareativa.strategy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 16:52
 */
@Component
public class PagamentoPix implements PagamentoStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoPix.class);

    @Override
    public Mono<Void> pagar(Double valor) {
        return Mono.fromRunnable(() ->
                        LOGGER.info("Processando PIX reativo de R$ {}", valor))
                .then(); // Retorna Mono<Void> ao finalizar
    }

    @Override
    public boolean aplica(String metodo) {
        return "PIX".equalsIgnoreCase(metodo);
    }
}


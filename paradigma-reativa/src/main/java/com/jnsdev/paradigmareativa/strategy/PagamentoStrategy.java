package com.jnsdev.paradigmareativa.strategy;


import reactor.core.publisher.Mono;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 16:51
 */
public interface PagamentoStrategy {

    // Retorna Mono para não bloquear a execução
    Mono<Void> pagar(Double valor);

    boolean aplica(String metodoPagamento);
}

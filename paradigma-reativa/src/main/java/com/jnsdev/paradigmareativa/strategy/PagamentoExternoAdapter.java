package com.jnsdev.paradigmareativa.strategy;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 17:00
 */
@Component
public class PagamentoExternoAdapter implements PagamentoStrategy {

    private final WebClient webClient;

    public PagamentoExternoAdapter(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.pagamento.com").build();
    }

    @Override
    public Mono<Void> pagar(Double valor) {
        return webClient.post()
                .uri("/v1/charge")
                .bodyValue(new Request(valor))
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public boolean aplica(String metodo) {
        return "EXTERNO".equalsIgnoreCase(metodo);
    }
}

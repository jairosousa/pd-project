package com.jndev.arquiterurahex.core.services;

import com.jndev.arquiterurahex.core.port.PaymentStrategy;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 08:31
 */
public class ProcessPaymentService {

    private final List<PaymentStrategy> strategies;

    public ProcessPaymentService(List<PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public void process(String method, Double amount) {
        PaymentStrategy strategy = strategies.stream()
                .filter(s -> s.isApplicable(method))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Método não suportado"));

        strategy.execute(amount);
    }
}

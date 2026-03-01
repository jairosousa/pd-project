package com.jndev.arquiteturahex.infrastructure.adapters;

import com.jndev.arquiteturahex.core.port.PaymentStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 08:34
 */
@Component
public class PayPalAdapter implements PaymentStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayPalAdapter.class);

    @Override
    public void execute(Double amount) {
        // Lógica específica para chamar a API do PayPal
        LOGGER.info("Processando R$ {} via PayPal SDK", amount);
    }

    @Override
    public boolean isApplicable(String paymentMethod) {
        return "PAYPAL".equalsIgnoreCase(paymentMethod);
    }
}

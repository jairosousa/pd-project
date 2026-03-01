package com.jndev.arquiterurahex.infrastructure.adaptres;


import com.jndev.arquiterurahex.core.port.PaymentStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 08:34
 */
@Component
public class MercadoPagodapter implements PaymentStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(MercadoPagodapter.class);

    @Override
    public void execute(Double amount) {
        // Lógica específica para chamar a API do PayPal
        LOGGER.info("Processando R$ {} via Mercado Pago SDK", amount);
    }

    @Override
    public boolean isApplicable(String paymentMethod) {
        return "MERCADOPAGO".equalsIgnoreCase(paymentMethod);
    }
}

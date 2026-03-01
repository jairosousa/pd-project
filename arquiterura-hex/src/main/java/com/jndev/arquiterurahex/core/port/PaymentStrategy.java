package com.jndev.arquiterurahex.core.port;


/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 08:30
 */
public interface PaymentStrategy {
    void execute(Double amount);

    boolean isApplicable(String paymentMethod);
}

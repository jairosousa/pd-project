package com.jndev.arquiterurahex.infrastructure.config;


import com.jndev.arquiterurahex.core.port.PaymentStrategy;
import com.jndev.arquiterurahex.core.services.ProcessPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 10:52
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ProcessPaymentService processPaymentService(List<PaymentStrategy> strategies) {
        return new ProcessPaymentService(strategies);
    }
}

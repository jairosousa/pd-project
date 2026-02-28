package com.jndev.arquiteturamvc.strategy;


import org.springframework.stereotype.Component;

/**
 * @Autor Jairo Nascimento
 * @Created 28/02/2026 - 19:28
 */
@Component
public class PagamentoPix implements PagamentoStrategy {
    @Override
    public void pagar(Double valor) {
        System.out.println("Gerando QR Code Pix de R$ " + valor);
    }

    @Override
    public boolean aplica(String metodo) {
        return "PIX".equalsIgnoreCase(metodo);
    }
}

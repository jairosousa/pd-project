package com.jndev.arquiteturamvc.strategy;


/**
 * @Autor Jairo Nascimento
 * @Created 28/02/2026 - 19:26
 */
public interface PagamentoStrategy {
    void pagar(Double valor);

    boolean aplica(String metodo);
}

package com.jndev.arquiteturamvc.servuce;


import com.jndev.arquiteturamvc.model.Pedido;
import com.jndev.arquiteturamvc.strategy.PagamentoStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 28/02/2026 - 19:29
 */
@Service
public class VendaService {

    // O Spring injeta TODAS as implementações da interface nesta lista
    private final List<PagamentoStrategy> estrategias;

    public VendaService(List<PagamentoStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public void finalizarVenda(Pedido pedido) {
        // Encontra a estratégia correta
        PagamentoStrategy strategy = estrategias.stream()
                .filter(s -> s.aplica(pedido.getMetodoPagamento()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Método de pagamento não suportado"));

        // Executa a regra
        strategy.pagar(pedido.getValor());
    }
}

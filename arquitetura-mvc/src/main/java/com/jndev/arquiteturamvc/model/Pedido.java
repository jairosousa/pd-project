package com.jndev.arquiteturamvc.model;


/**
 * @Autor Jairo Nascimento
 * @Created 28/02/2026 - 19:23
 */
public class Pedido {
    private String id;
    private Double valor;
    private String metodoPagamento; // Ex: "PIX", "BOLETO"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    // Getters e Setters
}

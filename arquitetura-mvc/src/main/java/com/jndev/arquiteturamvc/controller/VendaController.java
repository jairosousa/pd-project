package com.jndev.arquiteturamvc.controller;


import com.jndev.arquiteturamvc.model.Pedido;
import com.jndev.arquiteturamvc.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 28/02/2026 - 19:30
 */
@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<String> realizarVenda(@RequestBody Pedido pedido) {
        vendaService.finalizarVenda(pedido);
        return ResponseEntity.ok("Processamento iniciado!");
    }
}

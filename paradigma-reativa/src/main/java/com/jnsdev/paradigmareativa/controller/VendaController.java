package com.jnsdev.paradigmareativa.controller;


import com.jnsdev.paradigmareativa.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 16:58
 */
@RestController
@RequestMapping("/vendas")
@Tag(name = "Vendas", description = "Endpoints para processamento de pagamentos usando Strategy")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Operation(summary = "Realiza um pagamento",
            description = "Aceita diferentes métodos (PIX, CARTAO) via Strategy Pattern")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Pagamento aceito para processamento"),
            @ApiResponse(responseCode = "400", description = "Método de pagamento inválido")
    })
    @PostMapping("/{metodo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> realizarVenda(@PathVariable(name = "metodo") String metodo, @RequestBody Double valor) {
        return vendaService.finalizarVenda(metodo, valor);
    }
}

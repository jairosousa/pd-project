package com.jndev.arquiteturahex.application.web;


import com.jndev.arquiteturahex.core.services.ProcessPaymentService;
import org.springframework.web.bind.annotation.*;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 10:50
 */
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final ProcessPaymentService paymentService;

    public PaymentController(ProcessPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{method}")
    public void pay(@PathVariable(name = "method") String method, @RequestBody Double amount) {
        paymentService.process(method, amount);
    }
}

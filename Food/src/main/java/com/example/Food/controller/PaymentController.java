package com.example.Food.controller;

import com.example.Food.entity.Payment;
import com.example.Food.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@RequestParam Long orderId) {
        return paymentService.createPayment(orderId);
    }

    @PutMapping("/{paymentId}/complete")
    public Payment complete(@PathVariable Long paymentId) {
        return paymentService.completePayment(paymentId);
    }

    @PutMapping("/{paymentId}/fail")
    public Payment fail(@PathVariable Long paymentId) {
        return paymentService.failPayment(paymentId);
    }

    @GetMapping("/order/{orderId}")
    public Payment getByOrder(@PathVariable Long orderId) {
        return paymentService.getByOrder(orderId);
    }
}
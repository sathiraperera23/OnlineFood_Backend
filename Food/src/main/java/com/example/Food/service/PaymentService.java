package com.example.Food.service;

import com.example.Food.entity.Payment;

public interface PaymentService {

    Payment createPayment(Long orderId);

    Payment completePayment(Long paymentId);

    Payment failPayment(Long paymentId);

    Payment getByOrder(Long orderId);
}
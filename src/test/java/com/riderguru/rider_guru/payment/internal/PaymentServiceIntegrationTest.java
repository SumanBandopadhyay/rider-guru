package com.riderguru.rider_guru.payment.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class PaymentServiceIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void saveAndQueryPayment() {
        Payment payment = Payment.builder()
                .userId(456L)
                .amount(1000)
                .currency("INR")
                .razorpayId("rpay123")
                .build();

        Payment saved = paymentService.save(payment);
        assertNotNull(saved.getId());

        Optional<Payment> found = paymentService.getById(saved.getId());
        assertTrue(found.isPresent());

        Map<String, String> params = Map.of("userId", "456");
        List<Payment> result = paymentService.query(params);
        assertEquals(1, result.size());
        assertEquals(saved.getId(), result.get(0).getId());
    }
}

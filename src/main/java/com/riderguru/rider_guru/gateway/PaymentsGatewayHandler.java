package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.payment.PaymentDto;
import com.riderguru.rider_guru.payment.PaymentsAPI;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentsGatewayHandler {

    private final PaymentsAPI paymentsAPI;

    public PaymentsGatewayHandler(PaymentsAPI paymentsAPI) {
        this.paymentsAPI = paymentsAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        return paymentsAPI.create(paymentDto);
    }

    @GetMapping("/query")
    public ResponseEntity<List<PaymentDto>> queryPayments(@RequestParam(required = false) Map<String, String> params) {
        return paymentsAPI.query(params);
    }
}

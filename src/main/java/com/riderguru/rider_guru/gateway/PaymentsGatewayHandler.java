package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.payment.PaymentDto;
import com.riderguru.rider_guru.payment.PaymentsAPI;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Gateway controller for payment-related operations.
 *
 * <p>Delegates payment creation and query to {@link PaymentsAPI} while logging request parameters and results.</p>
 */
@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentsGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(PaymentsGatewayHandler.class);

    private final PaymentsAPI paymentsAPI;

    public PaymentsGatewayHandler(PaymentsAPI paymentsAPI) {
        this.paymentsAPI = paymentsAPI;
    }

    /**
     * Creates a new payment.
     *
     * @param paymentDto details of the payment
     * @return the created payment as response entity
     */
    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        logger.info("Received request to create payment: {}", paymentDto);
        ResponseEntity<PaymentDto> response = paymentsAPI.createPayment(paymentDto);
        logger.info("Response from createPayment: status={}, body={}", response.getStatusCode(), response.getBody());
        return response;
    }

    /**
     * Queries payments based on parameters.
     *
     * @param params optional query parameters (e.g., userId, date range, etc.)
     * @return list of payments as response entity
     */
    @GetMapping("/query")
    public ResponseEntity<List<PaymentDto>> queryPayments(@RequestParam(required = false) Map<String, String> params) {
        logger.info("Received request to query payments with params: {}", params);
        ResponseEntity<List<PaymentDto>> response = paymentsAPI.queryPayments(params);
        logger.info("Response from queryPayments: status={}, body size={}", response.getStatusCode(), response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }
}

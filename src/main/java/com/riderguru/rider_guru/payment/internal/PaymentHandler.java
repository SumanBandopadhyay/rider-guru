package com.riderguru.rider_guru.payment.internal;

import com.riderguru.rider_guru.payment.PaymentDto;
import com.riderguru.rider_guru.payment.PaymentsAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Handles payment related API calls.
 *
 * <p>Interacts with {@link PaymentService} and external Razorpay integration
 * while logging each call for visibility.</p>
 */
@Slf4j
@Component
class PaymentHandler implements PaymentsAPI {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final RazorpayAdapter razorpayAdapter;
    private final RazorpayMapper razorpayMapper;

    PaymentHandler(PaymentService paymentService, PaymentMapper paymentMapper, RazorpayAdapter razorpayAdapter, RazorpayMapper razorpayMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
        this.razorpayAdapter = razorpayAdapter;
        this.razorpayMapper = razorpayMapper;
    }

    @Override
    public ResponseEntity<PaymentDto> create(PaymentDto paymentDto) {
        log.info("Creating payment request for userId: {}", paymentDto.getUserId());
        paymentDto.setStatus("requested");
        Payment saved = paymentService.save(paymentMapper.toEntity(paymentDto));
        PaymentDto savedPaymentRequest = paymentMapper.toDto(saved);
        Map<String, Object> razorpayPaymentLinkRequest = razorpayMapper.fromPaymentRequest(savedPaymentRequest);
        Map<String, Object> razorpayPaymentLinkResponse = razorpayAdapter.createPaymentLink(razorpayPaymentLinkRequest);
        PaymentDto razorpayResponse = razorpayMapper.fromRazorpayResponse(razorpayPaymentLinkResponse);
        razorpayResponse.setId(savedPaymentRequest.getId());
        razorpayResponse.setUserId(savedPaymentRequest.getUserId());
        PaymentDto savedRazorpayResponse = paymentMapper.toDto(paymentService.save(paymentMapper.toEntity(razorpayResponse)));
        log.info("Payment request created with id: {}", savedRazorpayResponse.getId());
        return ResponseEntity.ok(savedRazorpayResponse);
    }

    @Override
    public ResponseEntity<PaymentDto> getById(Long id) {
        log.info("Fetching payment with id: {}", id);
        ResponseEntity<PaymentDto> response = paymentService.getById(id)
                .map(payment -> ResponseEntity.ok(paymentMapper.toDto(payment)))
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Fetch payment id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<PaymentDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<PaymentDto> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<PaymentDto>> query(Map<String, String> params) {
        log.info("Query payments with params: {}", params);
        ResponseEntity<List<PaymentDto>> response = ResponseEntity.ok(paymentService.query(params)
                .stream()
                .map(paymentMapper::toDto)
                .toList());
        log.info("Query returned {} payments", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<PaymentDto> update(PaymentDto paymentDto) {
        return null;
    }
}

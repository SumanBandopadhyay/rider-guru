package com.riderguru.rider_guru.payment.internal;

import com.riderguru.rider_guru.payment.PaymentDto;
import com.riderguru.rider_guru.payment.PaymentsAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        paymentDto.setStatus("requested");
        Payment saved = paymentService.save(paymentMapper.toEntity(paymentDto));
        PaymentDto savedPaymentRequest = paymentMapper.toDto(saved);
        Map<String, Object> razorpayPaymentLinkRequest = razorpayMapper.fromPaymentRequest(savedPaymentRequest);
        Map<String, Object> razorpayPaymentLinkResponse = razorpayAdapter.createPaymentLink(razorpayPaymentLinkRequest);
        PaymentDto razorpayResponse = razorpayMapper.fromRazorpayResponse(razorpayPaymentLinkResponse);
        PaymentDto savedRazorpayResponse = paymentMapper.toDto(paymentService.save(paymentMapper.toEntity(razorpayResponse)));
        return ResponseEntity.ok(savedRazorpayResponse);
    }

    @Override
    public ResponseEntity<PaymentDto> getById(Long id) {
        return paymentService.getById(id)
                .map(payment -> ResponseEntity.ok(paymentMapper.toDto(payment)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        return ResponseEntity.ok(paymentService.query(params)
                .stream()
                .map(paymentMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<PaymentDto> update(PaymentDto paymentDto) {
        return null;
    }
}

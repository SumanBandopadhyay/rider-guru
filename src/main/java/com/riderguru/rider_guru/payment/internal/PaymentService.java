package com.riderguru.rider_guru.payment.internal;

import com.riderguru.rider_guru.libs.GenericService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Slf4j
@Service
class PaymentService implements GenericService<Payment> {

    private final PaymentRepository paymentRepository;

    PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        log.info("Save payment : {}", payment.getReferenceId());
        Payment savedPayment = paymentRepository.save(payment);
        log.info("Saved payment id : {}", payment.getId());
        return savedPayment;
    }

    @Override
    public Optional<Payment> getById(Long id) {
        log.info("Payment check for id : {}", id);
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        log.info("Payment found for id {} : {}", id, optionalPayment.isPresent());
        return optionalPayment;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }

    @Override
    public List<Payment> query(Map<String, String> params) {
        log.info("Payment query : {}", params.toString());
        if (params.isEmpty()) {
            log.info("No criteria provided");
            return Collections.emptyList();
        }
        Specification<Payment> spec = Specification.where(PaymentSpecification.hasRazorpayId(params.get("razorpayId")));
        List<Payment> payments = paymentRepository.findAll(spec);
        log.info("Payment found : {}", !payments.isEmpty());
        return payments;
    }
}

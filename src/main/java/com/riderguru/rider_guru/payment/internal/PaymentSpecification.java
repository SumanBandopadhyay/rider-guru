package com.riderguru.rider_guru.payment.internal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

class PaymentSpecification {

    public static Specification<Payment> hasRazorpayId(String razorpayId) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(razorpayId) ? criteriaBuilder.equal(root.get("razorpayId"), razorpayId) : null;
    }

}

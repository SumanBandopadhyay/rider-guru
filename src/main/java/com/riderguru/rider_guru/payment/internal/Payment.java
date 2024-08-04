package com.riderguru.rider_guru.payment.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private String currency;
    private Boolean acceptPartial;
    private Integer firstMinPartialAmount;
    private Long expireBy;
    private String referenceId;
    private String description;
    private String customerName;
    private String customerContact;
    private String customerEmail;
    private Boolean notifySms;
    private Boolean notifyEmail;
    private Boolean reminderEnable;

    @Convert(converter = JsonAttributeConverter.class)
    @Column(columnDefinition = "JSON")
    private Map<String, String> notes;

    private String callbackUrl;
    private String callbackMethod;

    // New fields for response mapping
    private Integer amountPaid;
    private Long createdAt;
    private Long cancelledAt;
    private Long expiredAt;
    private String paymentLinkId;
    private String shortUrl;
    private String status;
    private Long updatedAt;
    private Boolean upiLink;
    private Boolean whatsappLink;
    private String razorpayId;
}

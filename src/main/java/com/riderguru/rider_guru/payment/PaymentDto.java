package com.riderguru.rider_guru.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("accept_partial")
    private Boolean acceptPartial;

    @JsonProperty("first_min_partial_amount")
    private Integer firstMinPartialAmount;

    @NotNull(message = "Expire by is required")
    @JsonProperty("expire_by")
    private Long expireBy;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "Customer details are required")
    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("notify")
    private Notify notify;

    @JsonProperty("reminder_enable")
    private Boolean reminderEnable;

//    @JsonProperty("notes")
//    private Map<String, String> notes;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("callback_method")
    private String callbackMethod;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Customer {
        @JsonProperty("name")
        private String name;

        @JsonProperty("contact")
        private String contact;

        @JsonProperty("email")
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Notify {
        @JsonProperty("sms")
        private Boolean sms;

        @JsonProperty("email")
        private Boolean email;
    }

    @JsonProperty("amount_paid")
    private Integer amountPaid;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("cancelled_at")
    private Long cancelledAt;

    @JsonProperty("expired_at")
    private Long expiredAt;

    @JsonProperty("payment_link_id")
    private String paymentLinkId;

    @JsonProperty("short_url")
    private String shortUrl;

    @JsonProperty("status")
    private String status;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("upi_link")
    private Boolean upiLink;

    @JsonProperty("whatsapp_link")
    private Boolean whatsappLink;

    @JsonProperty("razorpay_id")
    private String razorpayId;

}

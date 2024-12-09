package com.riderguru.rider_guru.payment.internal;

import com.riderguru.rider_guru.payment.PaymentDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class RazorpayMapper {
    public Map<String, Object> fromPaymentRequest(PaymentDto paymentDto) {
        Map<String, Object> map = new HashMap<>();

        map.put("amount", paymentDto.getAmount());
        map.put("currency", paymentDto.getCurrency());
        map.put("accept_partial", paymentDto.getAcceptPartial());
        map.put("first_min_partial_amount", paymentDto.getFirstMinPartialAmount());
        map.put("expire_by", paymentDto.getExpireBy());
        map.put("reference_id", paymentDto.getReferenceId());
        map.put("description", paymentDto.getDescription());

        // Customer
        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("name", paymentDto.getCustomer().getName());
        customerMap.put("contact", paymentDto.getCustomer().getContact());
        customerMap.put("email", paymentDto.getCustomer().getEmail());
        map.put("customer", customerMap);

        // Notify
        Map<String, Object> notifyMap = new HashMap<>();
        notifyMap.put("sms", paymentDto.getNotify().getSms());
        notifyMap.put("email", paymentDto.getNotify().getEmail());
        map.put("notify", notifyMap);

        map.put("reminder_enable", paymentDto.getReminderEnable());

        // Notes
//        map.put("notes", paymentDto.getNotes());

        map.put("callback_url", paymentDto.getCallbackUrl());
        map.put("callback_method", paymentDto.getCallbackMethod());

        return map;
    }

    public PaymentDto fromRazorpayResponse(Map<String, Object> response) {
        PaymentDto.PaymentDtoBuilder dtoBuilder = PaymentDto.builder();

        dtoBuilder.amount((Integer) response.get("amount"))
                .currency((String) response.get("currency"))
                .acceptPartial((Boolean) response.get("accept_partial"))
                .firstMinPartialAmount((Integer) response.get("first_min_partial_amount"))
                .expireBy(Long.parseLong(response.get("expire_by") + ""))
                .referenceId((String) response.get("reference_id"))
                .description((String) response.get("description"));

        Map<String, String> customerMap = (Map<String, String>) response.get("customer");
        if (customerMap != null) {
            PaymentDto.Customer customer = PaymentDto.Customer.builder()
                    .name(customerMap.get("name"))
                    .contact(customerMap.get("contact"))
                    .email(customerMap.get("email"))
                    .build();
            dtoBuilder.customer(customer);
        }

        Map<String, Boolean> notifyMap = (Map<String, Boolean>) response.get("notify");
        if (notifyMap != null) {
            PaymentDto.Notify notify = PaymentDto.Notify.builder()
                    .sms(notifyMap.get("sms"))
                    .email(notifyMap.get("email"))
                    .build();
            dtoBuilder.notify(notify);
        }

        dtoBuilder.reminderEnable((Boolean) response.get("reminder_enable"));

//        Map<String, String> notesMap = (Map<String, String>) response.get("notes");
//        if (notesMap != null) {
//            dtoBuilder.notes(notesMap);
//        }

        dtoBuilder.callbackUrl((String) response.get("callback_url"))
                .callbackMethod((String) response.get("callback_method"))
                .amountPaid((Integer) response.get("amount_paid"))
                .createdAt(((Integer) response.get("created_at")).longValue())
                .cancelledAt(Long.parseLong(response.get("cancelled_at") + ""))
                .expiredAt(Long.parseLong(response.get("expired_at") + ""))
                .paymentLinkId((String) response.get("id"))
                .shortUrl((String) response.get("short_url"))
                .status((String) response.get("status"))
                .updatedAt(Long.parseLong(response.get("updated_at") + ""))
                .upiLink((Boolean) response.get("upi_link"))
                .whatsappLink((Boolean) response.get("whatsapp_link"))
                .razorpayId((String) response.get("id"));

        return dtoBuilder.build();

    }
}

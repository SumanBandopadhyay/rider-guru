package com.riderguru.rider_guru.payment.internal;

import com.riderguru.rider_guru.libs.GenericMapper;
import com.riderguru.rider_guru.payment.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface PaymentMapper extends GenericMapper<Payment, PaymentDto> {
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "customerContact", source = "customer.contact")
    @Mapping(target = "customerEmail", source = "customer.email")
    @Mapping(target = "notifySms", source = "notify.sms")
    @Mapping(target = "notifyEmail", source = "notify.email")
    Payment toEntity(PaymentDto paymentDto);

    @Mapping(target = "customer.name", source = "customerName")
    @Mapping(target = "customer.contact", source = "customerContact")
    @Mapping(target = "customer.email", source = "customerEmail")
    @Mapping(target = "notify.sms", source = "notifySms")
    @Mapping(target = "notify.email", source = "notifyEmail")
    PaymentDto toDto(Payment payment);
}

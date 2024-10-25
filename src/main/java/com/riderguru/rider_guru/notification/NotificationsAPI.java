package com.riderguru.rider_guru.notification;

import com.riderguru.rider_guru.libs.GenericAPI;
import org.springframework.http.ResponseEntity;

public interface NotificationsAPI extends GenericAPI<NotificationDto> {
    ResponseEntity<String> sendOtp(String phone);
    ResponseEntity<Boolean> verifyOtp(String otp);
}

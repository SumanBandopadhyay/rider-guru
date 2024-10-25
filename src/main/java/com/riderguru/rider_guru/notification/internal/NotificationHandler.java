package com.riderguru.rider_guru.notification.internal;

import com.riderguru.rider_guru.notification.NotificationDto;
import com.riderguru.rider_guru.notification.NotificationsAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
class NotificationHandler implements NotificationsAPI {

    private final NotificationService notificationService;

    NotificationHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public ResponseEntity<NotificationDto> create(NotificationDto notificationDto) {
        return null;
    }

    @Override
    public ResponseEntity<NotificationDto> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<NotificationDto> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<NotificationDto>> query(Map<String, String> params) {
        return null;
    }

    @Override
    public ResponseEntity<String> sendOtp(String phone) {
        return ResponseEntity.ok("123456");
    }

    @Override
    public ResponseEntity<Boolean> verifyOtp(String otp) {
        if (otp.equals("123456")) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

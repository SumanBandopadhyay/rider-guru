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
    private final NotificationMapper notificationMapper;

    NotificationHandler(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public ResponseEntity<NotificationDto> create(NotificationDto notificationDto) {
        Notification notification = notificationService.save(notificationMapper.toEntity(notificationDto));
        return ResponseEntity.ok(notificationMapper.toDto(notification));
    }

    @Override
    public ResponseEntity<NotificationDto> getById(Long id) {
        return notificationService.getById(id)
                .map(n -> ResponseEntity.ok(notificationMapper.toDto(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getAll() {
        return ResponseEntity.ok(notificationService.getAll()
                .stream()
                .map(notificationMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<NotificationDto> delete(Long id) {
        return notificationService.getById(id)
                .map(n -> {
                    notificationService.delete(id);
                    return ResponseEntity.ok(notificationMapper.toDto(n));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<NotificationDto>> query(Map<String, String> params) {
        return ResponseEntity.ok(notificationService.query(params)
                .stream()
                .map(notificationMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<NotificationDto> update(NotificationDto notificationDto) {
        return notificationService.getById(notificationDto.getId())
                .map(n -> {
                    Notification saved = notificationService.save(notificationMapper.toEntity(notificationDto));
                    return ResponseEntity.ok(notificationMapper.toDto(saved));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
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

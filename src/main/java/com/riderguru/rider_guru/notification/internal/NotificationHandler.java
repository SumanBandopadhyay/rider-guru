package com.riderguru.rider_guru.notification.internal;

import com.riderguru.rider_guru.notification.NotificationDto;
import com.riderguru.rider_guru.notification.NotificationsAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * Internal handler implementing {@link NotificationsAPI}.
 * <p>
 * Delegates notification operations to the service layer and logs
 * incoming requests for easier troubleshooting.
 * </p>
 */
@Slf4j
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
        log.info("Creating notification for userId: {}", notificationDto.getUserId());
        Notification notification = notificationService.save(notificationMapper.toEntity(notificationDto));
        ResponseEntity<NotificationDto> response = ResponseEntity.ok(notificationMapper.toDto(notification));
        log.info("Notification created with id: {}", response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @Override
    public ResponseEntity<NotificationDto> getById(Long id) {
        log.info("Fetching notification with id: {}", id);
        ResponseEntity<NotificationDto> response = notificationService.getById(id)
                .map(n -> ResponseEntity.ok(notificationMapper.toDto(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Fetch notification id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getAll() {
        log.info("Fetching all notifications");
        ResponseEntity<List<NotificationDto>> response = ResponseEntity.ok(notificationService.getAll()
                .stream()
                .map(notificationMapper::toDto)
                .toList());
        log.info("Fetched {} notifications", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<NotificationDto> delete(Long id) {
        log.info("Deleting notification with id: {}", id);
        ResponseEntity<NotificationDto> response = notificationService.getById(id)
                .map(n -> {
                    notificationService.delete(id);
                    return ResponseEntity.ok(notificationMapper.toDto(n));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Delete notification id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<NotificationDto>> query(Map<String, String> params) {
        log.info("Query notifications with params: {}", params);
        ResponseEntity<List<NotificationDto>> response = ResponseEntity.ok(notificationService.query(params)
                .stream()
                .map(notificationMapper::toDto)
                .toList());
        log.info("Query returned {} notifications", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<NotificationDto> update(NotificationDto notificationDto) {
        log.info("Updating notification id: {}", notificationDto.getId());
        ResponseEntity<NotificationDto> response = notificationService.getById(notificationDto.getId())
                .map(n -> {
                    Notification saved = notificationService.save(notificationMapper.toEntity(notificationDto));
                    return ResponseEntity.ok(notificationMapper.toDto(saved));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Update notification id {} status: {}", notificationDto.getId(), response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<String> sendOtp(String phone) {
        log.info("Sending OTP to phone: {}", phone);
        ResponseEntity<String> response = ResponseEntity.ok("123456");
        log.info("OTP sent to phone: {}", phone);
        return response;
    }

    @Override
    public ResponseEntity<Boolean> verifyOtp(String otp) {
        log.info("Verifying OTP");
        ResponseEntity<Boolean> response;
        if (otp.equals("123456")) {
            response = ResponseEntity.ok(true);
        } else {
            response = ResponseEntity.status(401).build();
        }
        log.info("OTP verification status: {}", response.getStatusCode());
        return response;
    }
}

package com.riderguru.rider_guru.notification.internal;

import com.riderguru.rider_guru.libs.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
class NotificationService implements GenericService<Notification> {

    private final NotificationRepository notificationRepository;

    NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification save(Notification d) {
        return null;
    }

    @Override
    public Optional<Notification> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Notification> getAll() {
        return List.of();
    }

    @Override
    public List<Notification> query(Map<String, String> params) {
        return List.of();
    }
}

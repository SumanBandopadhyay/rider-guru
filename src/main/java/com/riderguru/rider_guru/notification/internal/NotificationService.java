package com.riderguru.rider_guru.notification.internal;

import com.riderguru.rider_guru.libs.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
class NotificationService implements GenericService<Notification> {

    private final NotificationRepository notificationRepository;

    NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification save(Notification d) {
        log.info("Save notification");
        Notification savedNotification = notificationRepository.save(d);
        log.info("Saved notification id : {}", savedNotification.getId());
        return savedNotification;
    }

    @Override
    public Optional<Notification> getById(Long id) {
        log.info("Notification check for id : {}", id);
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        log.info("Notification found for id {} : {}", id, optionalNotification.isPresent());
        return optionalNotification;
    }

    @Override
    public void delete(Long id) {
        log.info("Delete notification id : {}", id);
        notificationRepository.deleteById(id);
        log.info("Delete of notification id : {} complete", id);
    }

    @Override
    public List<Notification> getAll() {
        log.info("Listing all notifications");
        List<Notification> notifications = notificationRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        log.info("Total notifications : {}", notifications.size());
        return notifications;
    }

    @Override
    public List<Notification> query(Map<String, String> params) {
        log.info("Notification query : {}", params.toString());
        if (params.isEmpty()) {
            log.info("No criteria provided");
            return Collections.emptyList();
        }
        Long userId = parseLong(params.get("userId"));
        NotificationStatus status = parseStatus(params.get("status"));

        Specification<Notification> spec = Specification.where(NotificationSpecification.hasUserId(userId))
                .and(NotificationSpecification.hasStatus(status));

        List<Notification> notifications = notificationRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "createdAt"));
        log.info("Notifications found : {}", notifications.size());
        return notifications;
    }

    private Long parseLong(String value) {
        return StringUtils.hasText(value) ? Long.parseLong(value) : null;
    }

    private NotificationStatus parseStatus(String value) {
        return StringUtils.hasText(value) ? NotificationStatus.valueOf(value.toUpperCase()) : null;
    }
}

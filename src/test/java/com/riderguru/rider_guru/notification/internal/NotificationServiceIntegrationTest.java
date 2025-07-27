package com.riderguru.rider_guru.notification.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class NotificationServiceIntegrationTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    void saveAndQueryNotification() {
        Notification notification = Notification.builder()
                .userId(99L)
                .message("msg")
                .status(NotificationStatus.UNREAD)
                .createdAt(LocalDateTime.now())
                .build();

        Notification saved = notificationService.save(notification);
        assertNotNull(saved.getId());

        Optional<Notification> found = notificationService.getById(saved.getId());
        assertTrue(found.isPresent());

        Map<String, String> params = Map.of(
                "userId", "99",
                "status", "UNREAD"
        );
        List<Notification> result = notificationService.query(params);
        assertEquals(1, result.size());
        assertEquals(saved.getId(), result.get(0).getId());
    }
}

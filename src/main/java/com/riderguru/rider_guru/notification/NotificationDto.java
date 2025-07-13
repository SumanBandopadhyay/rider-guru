package com.riderguru.rider_guru.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private Long userId;
    private String message;
    private String status;
    private java.time.LocalDateTime createdAt;
}

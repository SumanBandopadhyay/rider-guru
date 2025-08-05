package com.riderguru.rider_guru.groups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO representing a pending member addition request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupJoinRequestDto {
    private Long id;
    private Long groupId;
    private Long requesterId;
    private Long requestedUserId;
    private String status;
    private LocalDateTime createdAt;
}


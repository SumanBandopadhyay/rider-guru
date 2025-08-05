package com.riderguru.rider_guru.groups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupMessageDto {
    private Long id;
    private Long groupId;
    private Long senderId;
    private String message;
    private LocalDateTime createdAt;
}

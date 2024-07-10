package com.riderguru.rider_guru.joining_points;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoiningPointsDto {
    private Long id;
    private String joiningPointName;
    private String joiningPointLocMap;
    private LocalDateTime scheduledJoiningTime;
    private LocalDateTime actualJoiningTime;
    private Boolean isActive;
    private Long tripId;
}

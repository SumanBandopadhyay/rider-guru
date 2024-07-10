package com.riderguru.rider_guru.trips;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripDto {
    private Long id;
    private String title;
    private String startPointName;
    private String startLocMap;
    private String endPointName;
    private String endLocMap;
    private LocalDateTime scheduledStartTime;
    private LocalDateTime actualStartTime;
    private LocalDateTime scheduledEndTime;
    private LocalDateTime actualEndTime;
    private Boolean isActive;
}

package com.riderguru.rider_guru.itinerary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryDto {
    private Long id;
    private String eventDescription;
    private String nodalPointName;
    private String nodalPointLocMap;
    private LocalDateTime nodalPointScheduledTime;
    private LocalDateTime nodalPointActualTime;
    private String banner;
    private Boolean isActive;
    private Long tripId;
}

package com.riderguru.rider_guru.itinerary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryDto {
    private Long id;
    private String eventDescription;
    private Long tripId;
}

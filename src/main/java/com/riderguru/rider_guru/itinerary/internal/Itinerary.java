package com.riderguru.rider_guru.itinerary.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "itinerary")
class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_description")
    private String eventDescription;
    @Column(name = "nodal_point_name")
    private String nodalPointName;
    @Column(name = "nodal_point_loc_map")
    private String nodalPointLocMap;
    @Column(name = "nodal_point_scheduled_time")
    private LocalDateTime nodalPointScheduledTime;
    @Column(name = "nodal_point_actual_time")
    private LocalDateTime nodalPointActualTime;
    @Column(name = "banner")
    private String banner;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "trip_id")
    private Long tripId;
}

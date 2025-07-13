package com.riderguru.rider_guru.trips.internal;

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
@Table(name = "trips")
class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "start_point_name")
    private String startPointName;
    @Column(name = "start_loc_map")
    private String startLocMap;
    @Column(name = "end_point_name")
    private String endPointName;
    @Column(name = "end_loc_map")
    private String endLocMap;
    @Column(name = "scheduled_start_time")
    private LocalDateTime scheduledStartTime;
    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;
    @Column(name = "scheduled_end_time")
    private LocalDateTime scheduledEndTime;
    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "user_id")
    private Long userId;
}

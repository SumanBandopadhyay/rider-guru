package com.riderguru.rider_guru.joining_points.internal;

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
@Table(name = "joining_points")
class JoiningPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "joining_point_name")
    private String joiningPointName;
    @Column(name = "joining_point_loc_map")
    private String joiningPointLocMap;
    @Column(name = "scheduled_joining_time")
    private LocalDateTime scheduledJoiningTime;
    @Column(name = "actual_joining_time")
    private LocalDateTime actualJoiningTime;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "trip_id")
    private Long tripId;
}

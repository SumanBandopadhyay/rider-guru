package com.riderguru.rider_guru.trips.internal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

class TripSpecification {

    public static Specification<Trip> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(title) ? criteriaBuilder.equal(root.get("title"), title) : null;
    }

    public static Specification<Trip> hasStartPointName(String startPointName) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(startPointName) ? criteriaBuilder.equal(root.get("startPointName"), startPointName) : null;
    }

    public static Specification<Trip> hasEndPointName(String endPointName) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(endPointName) ? criteriaBuilder.equal(root.get("endPointName"), endPointName) : null;
    }

    public static Specification<Trip> hasScheduledStartTime(LocalDateTime scheduledStartTime) {
        return (root, query, criteriaBuilder) ->
                scheduledStartTime != null ? criteriaBuilder.equal(root.get("scheduledStartTime"), scheduledStartTime) : null;
    }

    public static Specification<Trip> hasActualStartTime(LocalDateTime actualStartTime) {
        return (root, query, criteriaBuilder) ->
                actualStartTime != null ? criteriaBuilder.equal(root.get("actualStartTime"), actualStartTime) : null;
    }

    public static Specification<Trip> hasScheduledEndTime(LocalDateTime scheduledEndTime) {
        return (root, query, criteriaBuilder) ->
                scheduledEndTime != null ? criteriaBuilder.equal(root.get("scheduledEndTime"), scheduledEndTime) : null;
    }

    public static Specification<Trip> hasActualEndTime(LocalDateTime actualEndTime) {
        return (root, query, criteriaBuilder) ->
                actualEndTime != null ? criteriaBuilder.equal(root.get("actualEndTime"), actualEndTime) : null;
    }

    public static Specification<Trip> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) ->
                isActive != null ? criteriaBuilder.equal(root.get("isActive"), isActive) : null;
    }
}

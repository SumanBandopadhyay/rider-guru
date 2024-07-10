package com.riderguru.rider_guru.joining_points.internal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

class JoiningPointSpecification {

    public static Specification<JoiningPoint> hasJoiningPointName(String joiningPointName) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(joiningPointName) ? criteriaBuilder.equal(root.get("joiningPointName"), joiningPointName) : null;
    }

    public static Specification<JoiningPoint> hasJoiningPointLocMap(String joiningPointLocMap) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(joiningPointLocMap) ? criteriaBuilder.equal(root.get("joiningPointLocMap"), joiningPointLocMap) : null;
    }

    public static Specification<JoiningPoint> hasScheduledJoiningTime(LocalDateTime scheduledJoiningTime) {
        return (root, query, criteriaBuilder) ->
                scheduledJoiningTime != null ? criteriaBuilder.equal(root.get("scheduledJoiningTime"), scheduledJoiningTime) : null;
    }

    public static Specification<JoiningPoint> hasActualJoiningTime(LocalDateTime actualJoiningTime) {
        return (root, query, criteriaBuilder) ->
                actualJoiningTime != null ? criteriaBuilder.equal(root.get("actualJoiningTime"), actualJoiningTime) : null;
    }

    public static Specification<JoiningPoint> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) ->
                isActive != null ? criteriaBuilder.equal(root.get("isActive"), isActive) : null;
    }

    public static Specification<JoiningPoint> hasTripId(Long tripId) {
        return (root, query, criteriaBuilder) ->
                tripId != null ? criteriaBuilder.equal(root.get("tripId"), tripId) : null;
    }

}

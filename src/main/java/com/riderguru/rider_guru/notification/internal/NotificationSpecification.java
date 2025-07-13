package com.riderguru.rider_guru.notification.internal;

import org.springframework.data.jpa.domain.Specification;

class NotificationSpecification {

    public static Specification<Notification> hasUserId(Long userId) {
        return (root, query, criteriaBuilder) ->
                userId != null ? criteriaBuilder.equal(root.get("userId"), userId) : null;
    }

    public static Specification<Notification> hasStatus(NotificationStatus status) {
        return (root, query, criteriaBuilder) ->
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null;
    }
}

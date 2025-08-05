package com.riderguru.rider_guru.groups.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a request by a non-admin member to add another user to a group.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "group_join_requests")
class GroupJoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    /** The user who initiated the request. */
    @Column(name = "requester_id")
    private Long requesterId;

    /** The user who is requested to be added to the group. */
    @Column(name = "requested_user_id")
    private Long requestedUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GroupJoinRequestStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}


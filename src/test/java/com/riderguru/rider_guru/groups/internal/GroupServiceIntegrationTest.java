package com.riderguru.rider_guru.groups.internal;

import com.riderguru.rider_guru.notification.internal.Notification;
import com.riderguru.rider_guru.notification.internal.NotificationRepository;
import com.riderguru.rider_guru.notification.internal.NotificationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class GroupServiceIntegrationTest {

    @Autowired
    private GroupService groupService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void adminAddsMemberDirectly() {
        Group group = Group.builder()
                .name("Test Group")
                .adminId(1L)
                .build();
        Group savedGroup = groupService.save(group);

        GroupService.MemberAdditionResult result = groupService.addMember(savedGroup.getId(), 2L, 1L);
        assertNotNull(result.member());
        assertNull(result.request());
        List<GroupMember> members = groupService.getMembers(savedGroup.getId());
        assertEquals(1, members.size());
    }

    @Test
    void nonAdminRequestAndRejectionCreatesNotification() {
        Group group = Group.builder()
                .name("Test Group")
                .adminId(1L)
                .build();
        Group savedGroup = groupService.save(group);

        GroupService.MemberAdditionResult result = groupService.addMember(savedGroup.getId(), 2L, 3L);
        assertNull(result.member());
        assertNotNull(result.request());

        groupService.handleJoinRequest(result.request().getId(), 1L, false);

        List<Notification> notifications = notificationRepository.findAll();
        assertEquals(1, notifications.size());
        Notification n = notifications.get(0);
        assertEquals(3L, n.getUserId());
        assertEquals(NotificationStatus.UNREAD, n.getStatus());
    }

    @Test
    void adminApprovesRequestAddsMember() {
        Group group = Group.builder()
                .name("Test Group")
                .adminId(1L)
                .build();
        Group savedGroup = groupService.save(group);

        GroupService.MemberAdditionResult result = groupService.addMember(savedGroup.getId(), 2L, 3L);
        assertNull(result.member());

        GroupJoinRequest updated = groupService.handleJoinRequest(result.request().getId(), 1L, true);
        assertEquals(GroupJoinRequestStatus.APPROVED, updated.getStatus());

        List<GroupMember> members = groupService.getMembers(savedGroup.getId());
        assertEquals(1, members.size());
        assertEquals(2L, members.get(0).getUserId());
    }
}


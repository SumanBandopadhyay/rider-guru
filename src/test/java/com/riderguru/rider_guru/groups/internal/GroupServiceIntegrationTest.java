package com.riderguru.rider_guru.groups.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class GroupServiceIntegrationTest {

    @Autowired
    private GroupService groupService;

    @Test
    void createGroupAddMemberAndMessage() {
        Group group = Group.builder()
                .name("Test Group")
                .adminId(1L)
                .build();
        Group savedGroup = groupService.save(group);
        assertNotNull(savedGroup.getId());

        GroupMember member = GroupMember.builder()
                .groupId(savedGroup.getId())
                .userId(2L)
                .build();
        GroupMember savedMember = groupService.addMember(member);
        assertNotNull(savedMember.getId());
        List<GroupMember> members = groupService.getMembers(savedGroup.getId());
        assertEquals(1, members.size());

        GroupMessage message = GroupMessage.builder()
                .groupId(savedGroup.getId())
                .senderId(1L)
                .message("Hello")
                .createdAt(LocalDateTime.now())
                .build();
        GroupMessage savedMessage = groupService.saveMessage(message);
        assertNotNull(savedMessage.getId());
        List<GroupMessage> messages = groupService.getMessages(savedGroup.getId());
        assertEquals(1, messages.size());
    }
}

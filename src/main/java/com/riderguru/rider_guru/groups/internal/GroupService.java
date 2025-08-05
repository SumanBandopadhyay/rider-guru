package com.riderguru.rider_guru.groups.internal;

import com.riderguru.rider_guru.libs.GenericService;
import com.riderguru.rider_guru.notification.internal.Notification;
import com.riderguru.rider_guru.notification.internal.NotificationRepository;
import com.riderguru.rider_guru.notification.internal.NotificationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
class GroupService implements GenericService<Group> {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupMessageRepository groupMessageRepository;
    private final GroupJoinRequestRepository groupJoinRequestRepository;
    private final NotificationRepository notificationRepository;

    GroupService(GroupRepository groupRepository,
                 GroupMemberRepository groupMemberRepository,
                 GroupMessageRepository groupMessageRepository,
                 GroupJoinRequestRepository groupJoinRequestRepository,
                 NotificationRepository notificationRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.groupMessageRepository = groupMessageRepository;
        this.groupJoinRequestRepository = groupJoinRequestRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Group save(Group d) {
        return groupRepository.save(d);
    }

    @Override
    public Optional<Group> getById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> query(Map<String, String> params) {
        return groupRepository.findAll();
    }

    record MemberAdditionResult(GroupMember member, GroupJoinRequest request) { }

    MemberAdditionResult addMember(Long groupId, Long userId, Long requesterId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        if (requesterId != null && requesterId.equals(group.getAdminId())) {
            GroupMember saved = groupMemberRepository.save(GroupMember.builder()
                    .groupId(groupId)
                    .userId(userId)
                    .build());
            return new MemberAdditionResult(saved, null);
        }

        GroupJoinRequest request = GroupJoinRequest.builder()
                .groupId(groupId)
                .requesterId(requesterId)
                .requestedUserId(userId)
                .status(GroupJoinRequestStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        GroupJoinRequest savedRequest = groupJoinRequestRepository.save(request);
        return new MemberAdditionResult(null, savedRequest);
    }

    List<GroupMember> getMembers(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId);
    }

    GroupJoinRequest handleJoinRequest(Long requestId, Long adminId, boolean approve) {
        GroupJoinRequest request = groupJoinRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        if (!group.getAdminId().equals(adminId)) {
            throw new IllegalArgumentException("Only admin can approve or reject requests");
        }

        if (approve) {
            request.setStatus(GroupJoinRequestStatus.APPROVED);
            groupMemberRepository.save(GroupMember.builder()
                    .groupId(request.getGroupId())
                    .userId(request.getRequestedUserId())
                    .build());
        } else {
            request.setStatus(GroupJoinRequestStatus.REJECTED);
            notificationRepository.save(Notification.builder()
                    .userId(request.getRequesterId())
                    .message("Your request to add user %d to group %d was rejected".formatted(
                            request.getRequestedUserId(), request.getGroupId()))
                    .status(NotificationStatus.UNREAD)
                    .createdAt(LocalDateTime.now())
                    .build());
        }

        return groupJoinRequestRepository.save(request);
    }

    GroupMessage saveMessage(GroupMessage message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(LocalDateTime.now());
        }
        return groupMessageRepository.save(message);
    }

    List<GroupMessage> getMessages(Long groupId) {
        return groupMessageRepository.findByGroupId(groupId);
    }
}

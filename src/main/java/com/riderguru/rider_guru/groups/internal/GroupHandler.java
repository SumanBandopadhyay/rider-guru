package com.riderguru.rider_guru.groups.internal;

import com.riderguru.rider_guru.groups.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
class GroupHandler implements GroupsAPI {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    GroupHandler(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @Override
    public ResponseEntity<GroupDto> create(GroupDto groupDto) {
        log.info("Creating group: {}", groupDto.getName());
        Group saved = groupService.save(groupMapper.toEntity(groupDto));
        // Add the creator as the first member of the group
        groupService.addMember(saved.getId(), saved.getAdminId(), saved.getAdminId());
        return ResponseEntity.ok(groupMapper.toDto(saved));
    }

    @Override
    public ResponseEntity<GroupDto> getById(Long id) {
        return groupService.getById(id)
                .map(g -> ResponseEntity.ok(groupMapper.toDto(g)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<GroupDto>> getAll() {
        return ResponseEntity.ok(groupService.getAll().stream().map(groupMapper::toDto).toList());
    }

    @Override
    public ResponseEntity<GroupDto> delete(Long id) {
        return groupService.getById(id)
                .map(g -> {
                    groupService.delete(id);
                    return ResponseEntity.ok(groupMapper.toDto(g));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<GroupDto>> query(java.util.Map<String, String> params) {
        return ResponseEntity.ok(groupService.query(params).stream().map(groupMapper::toDto).toList());
    }

    @Override
    public ResponseEntity<GroupDto> update(GroupDto groupDto) {
        return groupService.getById(groupDto.getId())
                .map(g -> ResponseEntity.ok(groupMapper.toDto(groupService.save(groupMapper.toEntity(groupDto)))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<GroupMemberDto> addMember(Long groupId, GroupMemberDto memberDto) {
        memberDto.setGroupId(groupId);
        GroupService.MemberAdditionResult result = groupService.addMember(groupId, memberDto.getUserId(), memberDto.getRequesterId());
        if (result.member() != null) {
            return ResponseEntity.ok(groupMapper.toDto(result.member()));
        }
        // Request created for admin approval
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<GroupMemberDto>> getMembers(Long groupId) {
        List<GroupMemberDto> members = groupService.getMembers(groupId)
                .stream().map(groupMapper::toDto).toList();
        return ResponseEntity.ok(members);
    }

    @Override
    public ResponseEntity<GroupMessageDto> postMessage(Long groupId, GroupMessageDto messageDto) {
        messageDto.setGroupId(groupId);
        GroupMessage saved = groupService.saveMessage(groupMapper.toEntity(messageDto));
        return ResponseEntity.ok(groupMapper.toDto(saved));
    }

    @Override
    public ResponseEntity<List<GroupMessageDto>> getMessages(Long groupId) {
        List<GroupMessageDto> messages = groupService.getMessages(groupId)
                .stream().map(groupMapper::toDto).toList();
        return ResponseEntity.ok(messages);
    }

    @Override
    public ResponseEntity<GroupJoinRequestDto> respondToJoinRequest(Long requestId, Long adminId, Boolean approve) {
        GroupJoinRequest handled = groupService.handleJoinRequest(requestId, adminId, approve);
        return ResponseEntity.ok(groupMapper.toDto(handled));
    }
}

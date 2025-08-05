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
        GroupMember saved = groupService.addMember(groupMapper.toEntity(memberDto));
        return ResponseEntity.ok(groupMapper.toDto(saved));
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
}

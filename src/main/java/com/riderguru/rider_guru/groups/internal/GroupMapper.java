package com.riderguru.rider_guru.groups.internal;

import com.riderguru.rider_guru.groups.GroupDto;
import com.riderguru.rider_guru.groups.GroupMemberDto;
import com.riderguru.rider_guru.groups.GroupMessageDto;
import com.riderguru.rider_guru.libs.GenericMapper;
import org.springframework.stereotype.Component;

@Component
class GroupMapper implements GenericMapper<Group, GroupDto> {

    @Override
    public GroupDto toDto(Group e) {
        if (e == null) return null;
        return GroupDto.builder()
                .id(e.getId())
                .name(e.getName())
                .adminId(e.getAdminId())
                .build();
    }

    @Override
    public Group toEntity(GroupDto d) {
        if (d == null) return null;
        return Group.builder()
                .id(d.getId())
                .name(d.getName())
                .adminId(d.getAdminId())
                .build();
    }

    GroupMemberDto toDto(GroupMember e) {
        if (e == null) return null;
        return GroupMemberDto.builder()
                .id(e.getId())
                .groupId(e.getGroupId())
                .userId(e.getUserId())
                .build();
    }

    GroupMember toEntity(GroupMemberDto d) {
        if (d == null) return null;
        return GroupMember.builder()
                .id(d.getId())
                .groupId(d.getGroupId())
                .userId(d.getUserId())
                .build();
    }

    GroupMessageDto toDto(GroupMessage e) {
        if (e == null) return null;
        return GroupMessageDto.builder()
                .id(e.getId())
                .groupId(e.getGroupId())
                .senderId(e.getSenderId())
                .message(e.getMessage())
                .createdAt(e.getCreatedAt())
                .build();
    }

    GroupMessage toEntity(GroupMessageDto d) {
        if (d == null) return null;
        return GroupMessage.builder()
                .id(d.getId())
                .groupId(d.getGroupId())
                .senderId(d.getSenderId())
                .message(d.getMessage())
                .createdAt(d.getCreatedAt())
                .build();
    }
}

package com.riderguru.rider_guru.groups;

import com.riderguru.rider_guru.libs.GenericAPI;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupsAPI extends GenericAPI<GroupDto> {
    ResponseEntity<GroupMemberDto> addMember(Long groupId, GroupMemberDto memberDto);
    ResponseEntity<List<GroupMemberDto>> getMembers(Long groupId);
    ResponseEntity<GroupMessageDto> postMessage(Long groupId, GroupMessageDto messageDto);
    ResponseEntity<List<GroupMessageDto>> getMessages(Long groupId);
    ResponseEntity<GroupJoinRequestDto> respondToJoinRequest(Long requestId, Long adminId, Boolean approve);
}

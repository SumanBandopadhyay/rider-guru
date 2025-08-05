package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.groups.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin("*")
public class GroupsGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(GroupsGatewayHandler.class);

    private final GroupsAPI groupsAPI;

    public GroupsGatewayHandler(GroupsAPI groupsAPI) {
        this.groupsAPI = groupsAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDto> create(@Valid @RequestBody GroupDto groupDto) {
        logger.info("Creating group: {}", groupDto.getName());
        return groupsAPI.create(groupDto);
    }

    @PostMapping("/{groupId}/members")
    public ResponseEntity<GroupMemberDto> addMember(@PathVariable Long groupId, @Valid @RequestBody GroupMemberDto memberDto) {
        logger.info("Adding member {} to group {}", memberDto.getUserId(), groupId);
        return groupsAPI.addMember(groupId, memberDto);
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<List<GroupMemberDto>> getMembers(@PathVariable Long groupId) {
        logger.info("Fetching members for group {}", groupId);
        return groupsAPI.getMembers(groupId);
    }

    @PostMapping("/{groupId}/messages")
    public ResponseEntity<GroupMessageDto> postMessage(@PathVariable Long groupId, @Valid @RequestBody GroupMessageDto messageDto) {
        logger.info("Posting message in group {}", groupId);
        return groupsAPI.postMessage(groupId, messageDto);
    }

    @GetMapping("/{groupId}/messages")
    public ResponseEntity<List<GroupMessageDto>> getMessages(@PathVariable Long groupId) {
        logger.info("Fetching messages for group {}", groupId);
        return groupsAPI.getMessages(groupId);
    }
}

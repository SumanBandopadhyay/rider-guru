package com.riderguru.rider_guru.groups.internal;

import com.riderguru.rider_guru.libs.GenericService;
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

    GroupService(GroupRepository groupRepository,
                 GroupMemberRepository groupMemberRepository,
                 GroupMessageRepository groupMessageRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.groupMessageRepository = groupMessageRepository;
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

    GroupMember addMember(GroupMember member) {
        return groupMemberRepository.save(member);
    }

    List<GroupMember> getMembers(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId);
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

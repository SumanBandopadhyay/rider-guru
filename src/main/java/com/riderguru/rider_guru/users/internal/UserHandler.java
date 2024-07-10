package com.riderguru.rider_guru.users.internal;

import com.riderguru.rider_guru.users.UserDto;
import com.riderguru.rider_guru.users.UsersAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UserHandler implements UsersAPI {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserHandler(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<UserDto> create(UserDto userDto) {
        userDto.setIsActive(false);
        userDto.setIsPremium(false);
        userDto.setIsEmailVerified(false);
        return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toEntity(userDto))));
    }

    @Override
    public ResponseEntity<UserDto> getById(Long id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll()
                .stream()
                .map(userMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<UserDto> delete(Long id) {
        return userService.getById(id)
                .map(user -> {
                    userService.delete(user.getId());
                    return ResponseEntity.ok(userMapper.toDto(user));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<UserDto>> query(Map<String, String> params) {
        return ResponseEntity.ok(userService.query(params)
                .stream()
                .map(userMapper::toDto)
                .toList());
    }
}

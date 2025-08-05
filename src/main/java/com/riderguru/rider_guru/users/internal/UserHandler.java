package com.riderguru.rider_guru.users.internal;

import com.riderguru.rider_guru.users.UserDto;
import com.riderguru.rider_guru.users.UsersAPI;
import com.riderguru.rider_guru.libs.exceptions.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link UsersAPI} that delegates to {@link UserService}.
 * Logs each request to aid in debugging user operations.
 */
@Slf4j
@Component
class UserHandler implements UsersAPI {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserHandler(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<UserDto> create(UserDto userDto) {
        log.info("Creating user with mobileNumber: {}", userDto.getMobileNumber());
        userDto.setIsActive(false);
        userDto.setIsPremium(false);
        userDto.setIsEmailVerified(false);
        ResponseEntity<UserDto> response = ResponseEntity.ok(
                userMapper.toDto(userService.save(userMapper.toEntity(userDto))));
        log.info("User created with id: {}", response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @Override
    public ResponseEntity<UserDto> getById(Long id) {
        log.info("Fetching user with id: {}", id);
        ResponseEntity<UserDto> response = userService.getById(id)
                .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Fetch user id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("Fetching all users");
        ResponseEntity<List<UserDto>> response = ResponseEntity.ok(userService.getAll()
                .stream()
                .map(userMapper::toDto)
                .toList());
        log.info("Fetched {} users", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<UserDto> delete(Long id) {
        log.info("Deleting user with id: {}", id);
        ResponseEntity<UserDto> response = userService.getById(id)
                .map(user -> {
                    userService.delete(user.getId());
                    return ResponseEntity.ok(userMapper.toDto(user));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Delete user id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<UserDto>> query(Map<String, String> params) {
        log.info("Query users with params: {}", params);
        ResponseEntity<List<UserDto>> response = ResponseEntity.ok(userService.query(params)
                .stream()
                .map(userMapper::toDto)
                .toList());
        log.info("Query returned {} users", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<UserDto> update(UserDto userDto) {
        log.info("Updating user id: {}", userDto.getId());
        userService.getById(userDto.getId())
                .orElseThrow(() -> {
                    String message = "User ID " + userDto.getId() + " not present";
                    log.error(message);
                    return new GenericException(message);
                });
        ResponseEntity<UserDto> response = ResponseEntity.ok(
                userMapper.toDto(userService.save(userMapper.toEntity(userDto))));
        log.info("Update user id {} status: {}", userDto.getId(), response.getStatusCode());
        return response;
    }
}

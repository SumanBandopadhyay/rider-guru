package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.users.UserDto;
import com.riderguru.rider_guru.users.UsersAPI;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UsersGatewayHandler {

    private final UsersAPI usersAPI;

    public UsersGatewayHandler(UsersAPI usersAPI) {
        this.usersAPI = usersAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return usersAPI.create(userDto);
    }

    @GetMapping("/query")
    public ResponseEntity<List<UserDto>> getUser(@RequestParam(required = false) Map<String, String> params) {
        return usersAPI.query(params);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return usersAPI.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestParam("userid") Long userId) {
        return usersAPI.delete(userId);
    }
}

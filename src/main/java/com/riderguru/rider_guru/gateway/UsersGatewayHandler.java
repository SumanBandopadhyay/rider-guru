package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.users.UserDto;
import com.riderguru.rider_guru.users.UsersAPI;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Gateway controller for user operations.
 *
 * <p>Exposes endpoints to create, query, list and delete users.  Logging
 * captures the flow of requests and responses to aid debugging.</p>
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UsersGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(UsersGatewayHandler.class);

    private final UsersAPI usersAPI;

    public UsersGatewayHandler(UsersAPI usersAPI) {
        this.usersAPI = usersAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Creating user with phoneNumber: {}", userDto.getPhoneNumber());
        ResponseEntity<UserDto> response = usersAPI.create(userDto);
        logger.info("User creation finished with status: {} and returned id: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @GetMapping("/query")
    public ResponseEntity<List<UserDto>> getUser(@RequestParam(required = false) Map<String, String> params) {
        logger.info("Querying users with params: {}", params);
        ResponseEntity<List<UserDto>> response = usersAPI.query(params);
        logger.info("User query completed with status: {} and result count: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Fetching all users");
        ResponseEntity<List<UserDto>> response = usersAPI.getAll();
        logger.info("Fetched {} users", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestParam("userid") Long userId) {
        logger.info("Deleting user with id: {}", userId);
        ResponseEntity<UserDto> response = usersAPI.delete(userId);
        logger.info("User deletion finished with status: {} and body: {}", response.getStatusCode(), response.getBody());
        return response;
    }
}

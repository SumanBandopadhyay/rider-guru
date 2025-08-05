package com.riderguru.rider_guru.users.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void saveAndQueryUser() {
        User user = User.builder()
                .name("John")
                .email("john@example.com")
                .isEmailVerified(false)
                .mobileNumber("1234567890")
                .dob(new Date())
                .profileImage("img")
                .backgroundImage("bg")
                .profileWriteUp("About me")
                .sosEmergencyContact("911")
                .isActive(true)
                .isPremium(false)
                .build();

        User saved = userService.save(user);
        assertNotNull(saved.getId());

        Optional<User> found = userService.getById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("bg", found.get().getBackgroundImage());
        assertEquals("About me", found.get().getProfileWriteUp());

        Map<String, String> params = Map.of(
                "email", "john@example.com",
                "profileWriteUp", "About me",
                "backgroundImage", "bg");
        List<User> result = userService.query(params);
        assertEquals(1, result.size());
        assertEquals(saved.getId(), result.get(0).getId());
    }
}

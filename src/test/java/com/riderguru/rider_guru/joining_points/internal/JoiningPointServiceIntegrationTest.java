package com.riderguru.rider_guru.joining_points.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class JoiningPointServiceIntegrationTest {

    @Autowired
    private JoiningPointService joiningPointService;

    @Test
    void saveAndQueryJoiningPoint() {
        LocalDateTime now = LocalDateTime.now();
        JoiningPoint jp = JoiningPoint.builder()
                .joiningPointName("PointA")
                .joiningPointLocMap("Amap")
                .scheduledJoiningTime(now)
                .actualJoiningTime(now)
                .isActive(true)
                .tripId(1L)
                .build();

        JoiningPoint saved = joiningPointService.save(jp);
        assertNotNull(saved.getId());

        Optional<JoiningPoint> found = joiningPointService.getById(saved.getId());
        assertTrue(found.isPresent());

        Map<String, String> params = Map.of("joiningPointName", "PointA");
        List<JoiningPoint> result = joiningPointService.query(params);
        assertEquals(1, result.size());
        assertEquals(saved.getId(), result.get(0).getId());
    }
}

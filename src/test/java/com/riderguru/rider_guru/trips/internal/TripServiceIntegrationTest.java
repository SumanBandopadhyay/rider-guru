package com.riderguru.rider_guru.trips.internal;

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
class TripServiceIntegrationTest {

    @Autowired
    private TripService tripService;

    @Test
    void saveAndQueryTrip() {
        LocalDateTime now = LocalDateTime.now();
        Trip trip = Trip.builder()
                .title("Test Trip")
                .startPointName("A")
                .startLocMap("Amap")
                .endPointName("B")
                .endLocMap("Bmap")
                .scheduledStartTime(now)
                .actualStartTime(now)
                .scheduledEndTime(now.plusHours(1))
                .actualEndTime(now.plusHours(1))
                .isActive(true)
                .userId(123L)
                .build();

        Trip saved = tripService.save(trip);
        assertNotNull(saved.getId());

        Optional<Trip> found = tripService.getById(saved.getId());
        assertTrue(found.isPresent());

        Map<String, String> params = Map.of("userId", "123");
        List<Trip> result = tripService.query(params);
        assertEquals(1, result.size());
        assertEquals(saved.getId(), result.get(0).getId());
    }
}

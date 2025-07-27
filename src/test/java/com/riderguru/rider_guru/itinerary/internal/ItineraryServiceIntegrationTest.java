package com.riderguru.rider_guru.itinerary.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ItineraryServiceIntegrationTest {

    @Autowired
    private ItineraryService itineraryService;

    @Test
    void saveAndFetchItinerary() {
        LocalDateTime now = LocalDateTime.now();
        Itinerary itinerary = Itinerary.builder()
                .eventDescription("Event")
                .nodalPointName("Node")
                .nodalPointLocMap("NMap")
                .nodalPointScheduledTime(now)
                .nodalPointActualTime(now)
                .banner("banner")
                .isActive(true)
                .tripId(1L)
                .build();

        Itinerary saved = itineraryService.save(itinerary);
        assertNotNull(saved.getId());

        Optional<Itinerary> found = itineraryService.getById(saved.getId());
        assertTrue(found.isPresent());

        List<Itinerary> all = itineraryService.getAll();
        assertFalse(all.isEmpty());
    }
}

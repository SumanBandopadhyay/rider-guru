package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.itinerary.ItineraryAPI;
import com.riderguru.rider_guru.itinerary.ItineraryDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Gateway controller for itineraries.
 *
 * <p>Handles creation, querying, listing and deletion of itineraries.  Logging
 * is added for each endpoint to trace user interactions and responses.</p>
 */
@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin("*")
public class ItineraryGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryGatewayHandler.class);

    private final ItineraryAPI itineraryAPI;

    public ItineraryGatewayHandler(ItineraryAPI itineraryAPI) {
        this.itineraryAPI = itineraryAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<ItineraryDto> createItinerary(@Valid @RequestBody ItineraryDto itineraryDto) {
        logger.info("Creating itinerary for tripId: {}", itineraryDto.getTripId());
        ResponseEntity<ItineraryDto> response = itineraryAPI.create(itineraryDto);
        logger.info("Itinerary creation completed with status: {} and returned id: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @GetMapping("/query")
    public ResponseEntity<List<ItineraryDto>> queryItineraries(@RequestParam(required = false) Map<String, String> params) {
        logger.info("Querying itinerary with params: {}", params);
        ResponseEntity<List<ItineraryDto>> response = itineraryAPI.query(params);
        logger.info("Itinerary query completed with status: {} and result count: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItineraryDto>> getAllItineraries() {
        logger.info("Fetching all itineraries");
        ResponseEntity<List<ItineraryDto>> response = itineraryAPI.getAll();
        logger.info("Fetched {} itineraries", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ItineraryDto> deleteItinerary(@RequestParam("itinerary-id") Long itineraryId) {
        logger.info("Deleting itinerary with id: {}", itineraryId);
        ResponseEntity<ItineraryDto> response = itineraryAPI.delete(itineraryId);
        logger.info("Itinerary deletion completed with status: {} and body: {}", response.getStatusCode(), response.getBody());
        return response;
    }
}

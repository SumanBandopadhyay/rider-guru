package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.trips.TripDto;
import com.riderguru.rider_guru.trips.TripsAPI;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Gateway controller for trip-related operations.
 *
 * <p>Delegates trip creation, update, retrieval, deletion and query to {@link TripsAPI} while logging request parameters and results.</p>
 */
@RestController
@RequestMapping("/api/trips")
@CrossOrigin("*")
public class TripsGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(TripsGatewayHandler.class);

    private final TripsAPI tripsAPI;

    public TripsGatewayHandler(TripsAPI tripsAPI) {
        this.tripsAPI = tripsAPI;
    }

    /**
     * Creates a new trip.
     *
     * @param tripDto trip details
     * @return created trip as response entity
     */
    @PostMapping("/create")
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripDto tripDto) {
        logger.info("Received request to create trip: {}", tripDto);
        ResponseEntity<TripDto> response = tripsAPI.create(tripDto);
        logger.info("Response from createTrip: status={}, body={}", response.getStatusCode(), response.getBody());
        return response;
    }

    /**
     * Updates an existing trip.
     *
     * @param tripDto trip details for update
     * @return updated trip as response entity
     */
    @PostMapping("/update")
    public ResponseEntity<TripDto> updateTrip(@Valid @RequestBody TripDto tripDto) {
        logger.info("Received request to update trip: {}", tripDto);
        ResponseEntity<TripDto> response = tripsAPI.update(tripDto);
        logger.info("Response from updateTrip: status={}, body={}", response.getStatusCode(), response.getBody());
        return response;
    }

    /**
     * Retrieves all trips.
     *
     * @return list of trips as response entity
     */
    @GetMapping("/all")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        logger.info("Received request to get all trips");
        ResponseEntity<List<TripDto>> response = tripsAPI.getAll();
        logger.info("Response from getAllTrips: status={}, body size={}", response.getStatusCode(), response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    /**
     * Deletes a trip.
     *
     * @param tripId ID of trip to delete
     * @return deletion response entity
     */
    @PostMapping("/delete")
    public ResponseEntity<TripDto> deleteTrip(@RequestParam Long tripId) {
        logger.info("Received request to delete trip with id: {}", tripId);
        ResponseEntity<TripDto> response = tripsAPI.delete(tripId);
        logger.info("Response from deleteTrip: status={}, body={}", response.getStatusCode(), response.getBody());
        return response;
    }

    /**
     * Queries trips based on parameters.
     *
     * @param params optional query parameters
     * @return list of matching trips as response entity
     */
    @GetMapping("/query")
    public ResponseEntity<List<TripDto>> queryTrips(@RequestParam(required = false) Map<String, String> params) {
        logger.info("Received request to query trips with params: {}", params);
        ResponseEntity<List<TripDto>> response = tripsAPI.query(params);
        logger.info("Response from queryTrips: status={}, body size={}", response.getStatusCode(), response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }
}

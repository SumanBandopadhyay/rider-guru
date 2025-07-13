package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.trips.TripDto;
import com.riderguru.rider_guru.trips.TripsAPI;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin("*")
public class TripsGatewayHandler {

    private final TripsAPI tripsAPI;

    public TripsGatewayHandler(TripsAPI tripsAPI) {
        this.tripsAPI = tripsAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripDto tripDto) {
        return tripsAPI.create(tripDto);
    }

    @PostMapping("/update")
    public ResponseEntity<TripDto> updateTrip(@Valid @RequestBody TripDto tripDto) {
        return tripsAPI.update(tripDto);
    }

    @GetMapping("/query")
    public ResponseEntity<List<TripDto>> queryTrip(@RequestParam(required = false) Map<String, String> params) {
        return tripsAPI.query(params);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        return tripsAPI.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TripDto> deleteTrip(@RequestParam("trip_id") Long tripId) {
        return tripsAPI.delete(tripId);
    }
}

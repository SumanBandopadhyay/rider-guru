package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.itinerary.ItineraryAPI;
import com.riderguru.rider_guru.itinerary.ItineraryDto;
import com.riderguru.rider_guru.trips.TripDto;
import com.riderguru.rider_guru.trips.TripsAPI;
import com.riderguru.rider_guru.users.UserDto;
import com.riderguru.rider_guru.users.UsersAPI;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GatewayHandler {

    private final UsersAPI usersAPI;
    private final TripsAPI tripsAPI;
    private final ItineraryAPI itineraryAPI;

    public GatewayHandler(UsersAPI usersAPI, TripsAPI tripsAPI, ItineraryAPI itineraryAPI) {
        this.usersAPI = usersAPI;
        this.tripsAPI = tripsAPI;
        this.itineraryAPI = itineraryAPI;
    }

    @PostMapping("/users/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return usersAPI.create(userDto);
    }

    @GetMapping("/users/query")
    public ResponseEntity<List<UserDto>> getUser(@RequestParam(required = false) Map<String, String> params) {
        return usersAPI.query(params);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return usersAPI.getAll();
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestParam("userid") Long userId) {
        return usersAPI.delete(userId);
    }

    @PostMapping("/trips/create")
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripDto tripDto) {
        return tripsAPI.create(tripDto);
    }

    @GetMapping("/trips/query")
    public ResponseEntity<List<TripDto>> queryTrip(@RequestParam(required = false) Map<String, String> params) {
        return tripsAPI.query(params);
    }

    @GetMapping("/trips/all")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        return tripsAPI.getAll();
    }

    @DeleteMapping("/trips/delete")
    public ResponseEntity<TripDto> deleteTrip(@RequestParam("trip-id") Long tripId) {
        return tripsAPI.delete(tripId);
    }

    @PostMapping("/itinerary/create")
    public ResponseEntity<ItineraryDto> createItinerary(@Valid @RequestBody ItineraryDto itineraryDto) {
        return itineraryAPI.create(itineraryDto);
    }

    @GetMapping("/itinerary/query")
    public ResponseEntity<List<ItineraryDto>> queryItinerary(@RequestParam(required = false) Map<String, String> params) {
        return itineraryAPI.query(params);
    }

    @GetMapping("/itinerary/all")
    public ResponseEntity<List<ItineraryDto>> getAllItinerary() {
        return itineraryAPI.getAll();
    }

    @DeleteMapping("/itinerary/delete")
    public ResponseEntity<ItineraryDto> deleteItinerary(@RequestParam("itinerary-id") Long itineraryId) {
        return itineraryAPI.delete(itineraryId);
    }

}

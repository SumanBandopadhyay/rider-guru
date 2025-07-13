package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.itinerary.ItineraryAPI;
import com.riderguru.rider_guru.itinerary.ItineraryDto;
import com.riderguru.rider_guru.joining_points.JoiningPointsAPI;
import com.riderguru.rider_guru.joining_points.JoiningPointsDto;
import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.MapsAPI;
import com.riderguru.rider_guru.map.PlaceDto;
import com.riderguru.rider_guru.notification.NotificationsAPI;
import com.riderguru.rider_guru.notification.NotificationDto;
import com.riderguru.rider_guru.payment.PaymentDto;
import com.riderguru.rider_guru.payment.PaymentsAPI;
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
@CrossOrigin("*")
public class GatewayHandler {

    private final UsersAPI usersAPI;
    private final TripsAPI tripsAPI;
    private final ItineraryAPI itineraryAPI;
    private final JoiningPointsAPI joiningPointsAPI;
    private final NotificationsAPI notificationsAPI;
    private final PaymentsAPI paymentsAPI;
    private final MapsAPI mapsAPI;

    public GatewayHandler(UsersAPI usersAPI, TripsAPI tripsAPI, ItineraryAPI itineraryAPI, JoiningPointsAPI joiningPointsAPI, NotificationsAPI notificationsAPI, PaymentsAPI paymentsAPI, MapsAPI mapsAPI) {
        this.usersAPI = usersAPI;
        this.tripsAPI = tripsAPI;
        this.itineraryAPI = itineraryAPI;
        this.joiningPointsAPI = joiningPointsAPI;
        this.notificationsAPI = notificationsAPI;
        this.paymentsAPI = paymentsAPI;
        this.mapsAPI = mapsAPI;
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

    @PostMapping("/trips/update")
    public ResponseEntity<TripDto> updateTrip(@Valid @RequestBody TripDto tripDto) {
        return tripsAPI.update(tripDto);
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
    public ResponseEntity<TripDto> deleteTrip(@RequestParam("trip_id") Long tripId) {
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

    @PostMapping("/joining_points/create")
    public ResponseEntity<JoiningPointsDto> createJoiningPoints(@Valid @RequestBody JoiningPointsDto joiningPointsDto) {
        return joiningPointsAPI.create(joiningPointsDto);
    }

    @GetMapping("/joining_points/query")
    public ResponseEntity<List<JoiningPointsDto>> queryJoiningPoints(@RequestParam(required = false) Map<String, String> params) {
        return joiningPointsAPI.query(params);
    }

    @GetMapping("/joining_points/all")
    public ResponseEntity<List<JoiningPointsDto>> getAllJoiningPoints() {
        return joiningPointsAPI.getAll();
    }

    @DeleteMapping("/joining_points/delete")
    public ResponseEntity<JoiningPointsDto> deleteJoiningPoints(@RequestParam("joining-point-id") Long joiningPointId) {
        return joiningPointsAPI.delete(joiningPointId);
    }

    @GetMapping("/notifications/otp/send")
    public ResponseEntity<String> sendOtp(@RequestParam("phone-number") String phoneNumber) {
        return notificationsAPI.sendOtp(phoneNumber);
    }

    @GetMapping("/notifications/otp/verify")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam("otp") String otp) {
        return notificationsAPI.verifyOtp(otp);
    }

    @GetMapping("/notifications/query")
    public ResponseEntity<List<NotificationDto>> queryNotifications(@RequestParam(required = false) Map<String, String> params) {
        return notificationsAPI.query(params);
    }

    @PostMapping("/payments/create")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        return paymentsAPI.create(paymentDto);
    }

    @GetMapping("/payments/query")
    public ResponseEntity<List<PaymentDto>> queryPayments(@RequestParam(required = false) Map<String, String> params) {
        return paymentsAPI.query(params);
    }

    @GetMapping("/map/place/search")
    public ResponseEntity<List<PlaceDto>> searchPlace(@RequestParam(value = "key") String placeKey) {
        return mapsAPI.searchPlace(placeKey);
    }

    @GetMapping("/map/place/location")
    public ResponseEntity<LocationDto> getLocation(@RequestParam(value = "place_id") String placeId) {
        return mapsAPI.getLocation(placeId);
    }

    @GetMapping("/map/place/route")
    public ResponseEntity<String> getRoute(@RequestParam(value = "origin_lat") double originLat,
                                           @RequestParam(value = "origin_lng") double originLng,
                                           @RequestParam(value = "dest_lat") double destLat,
                                           @RequestParam(value = "dest_lng") double destLng) {
        return mapsAPI.getRoute(originLat, originLng, destLat, destLng);
    }

}

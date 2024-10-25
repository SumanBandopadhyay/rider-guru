package com.riderguru.rider_guru.map;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MapsAPI {
    ResponseEntity<List<PlaceDto>> searchPlace(String searchKeyword);
    ResponseEntity<LocationDto> getLocation(String placeId);
    ResponseEntity<String> getRoute(double originLat, double originLng, double destLat, double destLng);
}
 
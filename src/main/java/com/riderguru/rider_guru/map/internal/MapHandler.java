package com.riderguru.rider_guru.map.internal;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.MapsAPI;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for map related operations backed by {@link MapService}.
 * Logs requests to help trace interactions with the external map provider.
 */
@Slf4j
@Component
class MapHandler implements MapsAPI {

    private final MapService mapService;

    MapHandler(MapService mapService) {
        this.mapService = mapService;
    }

    @Override
    public ResponseEntity<List<PlaceDto>> searchPlace(String searchKeyword) {
        log.info("Searching for places with keyword: {}", searchKeyword);
        ResponseEntity<List<PlaceDto>> response = ResponseEntity.ok(mapService.searchPlace(searchKeyword));
        log.info("Found {} places", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<LocationDto> getLocation(String placeId) {
        log.info("Fetching location for placeId: {}", placeId);
        ResponseEntity<LocationDto> response = ResponseEntity.ok(mapService.locationForPlaceId(placeId));
        log.info("Fetched location for placeId: {}", placeId);
        return response;
    }

    @Override
    public ResponseEntity<String> getRoute(double originLat, double originLng, double destLat, double destLng) {
        log.info("Fetching route from ({}, {}) to ({}, {})", originLat, originLng, destLat, destLng);
        ResponseEntity<String> response = ResponseEntity.ok(mapService.getRoute(originLat, originLng, destLat, destLng));
        log.info("Route retrieval status: {}", response.getStatusCode());
        return response;
    }
}

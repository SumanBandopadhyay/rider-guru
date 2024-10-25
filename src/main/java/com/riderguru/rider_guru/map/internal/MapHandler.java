package com.riderguru.rider_guru.map.internal;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.MapsAPI;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
class MapHandler implements MapsAPI {

    private final MapService mapService;

    MapHandler(MapService mapService) {
        this.mapService = mapService;
    }

    @Override
    public ResponseEntity<List<PlaceDto>> searchPlace(String searchKeyword) {
        return ResponseEntity.ok(mapService.searchPlace(searchKeyword));
    }

    @Override
    public ResponseEntity<LocationDto> getLocation(String placeId) {
        return ResponseEntity.ok(mapService.locationForPlaceId(placeId));
    }

    @Override
    public ResponseEntity<String> getRoute(double originLat, double originLng, double destLat, double destLng) {
        return ResponseEntity.ok(mapService.getRoute(originLat, originLng, destLat, destLng));
    }
}

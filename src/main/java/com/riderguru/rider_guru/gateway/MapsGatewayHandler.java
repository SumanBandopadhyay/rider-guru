package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.MapsAPI;
import com.riderguru.rider_guru.map.PlaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/map")
@CrossOrigin("*")
public class MapsGatewayHandler {

    private final MapsAPI mapsAPI;

    public MapsGatewayHandler(MapsAPI mapsAPI) {
        this.mapsAPI = mapsAPI;
    }

    @GetMapping("/place/search")
    public ResponseEntity<List<PlaceDto>> searchPlace(@RequestParam(value = "key") String placeKey) {
        return mapsAPI.searchPlace(placeKey);
    }

    @GetMapping("/place/location")
    public ResponseEntity<LocationDto> getLocation(@RequestParam(value = "place_id") String placeId) {
        return mapsAPI.getLocation(placeId);
    }

    @GetMapping("/place/route")
    public ResponseEntity<String> getRoute(@RequestParam(value = "origin_lat") double originLat,
                                           @RequestParam(value = "origin_lng") double originLng,
                                           @RequestParam(value = "dest_lat") double destLat,
                                           @RequestParam(value = "dest_lng") double destLng) {
        return mapsAPI.getRoute(originLat, originLng, destLat, destLng);
    }
}

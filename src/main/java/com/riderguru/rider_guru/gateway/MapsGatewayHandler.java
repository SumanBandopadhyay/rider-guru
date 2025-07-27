package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.MapsAPI;
import com.riderguru.rider_guru.map.PlaceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Gateway controller for map-related operations.
 *
 * <p>Delegates place search, location lookup and route calculation to
 * {@link MapsAPI} while logging request parameters and results.</p>
 */
@RestController
@RequestMapping("/api/map")
@CrossOrigin("*")
public class MapsGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(MapsGatewayHandler.class);

    private final MapsAPI mapsAPI;

    public MapsGatewayHandler(MapsAPI mapsAPI) {
        this.mapsAPI = mapsAPI;
    }

    @GetMapping("/place/search")
    public ResponseEntity<List<PlaceDto>> searchPlace(@RequestParam(value = "key") String placeKey) {
        logger.info("Searching for places with key: {}", placeKey);
        ResponseEntity<List<PlaceDto>> response = mapsAPI.searchPlace(placeKey);
        logger.info("Place search finished with status: {} and result count: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @GetMapping("/place/location")
    public ResponseEntity<LocationDto> getLocation(@RequestParam(value = "place_id") String placeId) {
        logger.info("Getting location for placeId: {}", placeId);
        ResponseEntity<LocationDto> response = mapsAPI.getLocation(placeId);
        logger.info("Location lookup finished with status: {}", response.getStatusCode());
        return response;
    }

    @GetMapping("/place/route")
    public ResponseEntity<String> getRoute(@RequestParam(value = "origin_lat") double originLat,
                                           @RequestParam(value = "origin_lng") double originLng,
                                           @RequestParam(value = "dest_lat") double destLat,
                                           @RequestParam(value = "dest_lng") double destLng) {
        logger.info("Calculating route from ({} , {}) to ({} , {})", originLat, originLng, destLat, destLng);
        ResponseEntity<String> response = mapsAPI.getRoute(originLat, originLng, destLat, destLng);
        logger.info("Route calculation finished with status: {}", response.getStatusCode());
        return response;
    }
}

package com.riderguru.rider_guru.map.internal;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that provides map-related operations by delegating to {@link MapAdapter}.
 * It supports searching for places, retrieving location details for a place ID,
 * and computing routes between coordinates.
 */
@Slf4j
@Service
public class MapService {

    private final MapAdapter mapAdapter;

    /**
     * Constructs a MapService with the given map adapter.
     *
     * @param mapAdapter the map adapter used to perform map operations
     */
    public MapService(MapAdapter mapAdapter) {
        this.mapAdapter = mapAdapter;
    }

    /**
     * Searches for places that match the given search keyword.
     *
     * @param searchKeyword the search text used to find places
     * @return list of place results matching the search keyword
     */
    public List<PlaceDto> searchPlace(String searchKeyword) {
        log.info("Searching for places with keyword: {}", searchKeyword);
        List<PlaceDto> placeList = mapAdapter.searchPlace(searchKeyword);
        log.info("Found {} places for keyword: {}", placeList.size(), searchKeyword);
        return placeList;
    }

    /**
     * Gets the location details (latitude/longitude) for a specified place ID.
     *
     * @param placeId the Google Place ID for which to retrieve location details
     * @return the location details for the specified place ID
     */
    public LocationDto locationForPlaceId(String placeId) {
        log.info("Retrieving location for placeId: {}", placeId);
        try {
            LocationDto location = mapAdapter.locationForPlaceId(placeId);
            log.info("Location for placeId {}: {}", placeId, location);
            return location;
        } catch (Exception e) {
            log.error("Failed to get location for placeId {}: {}", placeId, e.getMessage(), e);
            throw new RuntimeException("Lat-Long not found for placeId : " + placeId, e);
        }
    }

    /**
     * Computes the route between the origin and destination coordinates.
     *
     * @param originLat latitude of the origin
     * @param originLng longitude of the origin
     * @param destLat   latitude of the destination
     * @param destLng   longitude of the destination
     * @return a string representing the route details
     */
    public String getRoute(double originLat, double originLng, double destLat, double destLng) {
        log.info("Getting route from ({}, {}) to ({}, {})", originLat, originLng, destLat, destLng);
        String route = mapAdapter.getRoute(originLat, originLng, destLat, destLng);
        log.info("Route retrieved between origin ({}, {}) and destination ({}, {})", originLat, originLng, destLat, destLng);
        return route;
    }
}

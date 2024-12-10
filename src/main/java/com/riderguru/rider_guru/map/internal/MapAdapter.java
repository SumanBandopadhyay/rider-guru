package com.riderguru.rider_guru.map.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
public class MapAdapter {

    private final RestClient restClient;
    private final String googleMapKey;

    public MapAdapter(RestClient restClient,
                      @Value("${google.map.key}")
                      String googleMapKey) {
        this.restClient = restClient;
        this.googleMapKey = googleMapKey;
    }

    List<PlaceDto> searchPlace(String searchKeyword) {
        PlaceResponse placeResponse = restClient
                .get()
                .uri("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + searchKeyword +
                        "&types=geocode" +
                        "&key=" + googleMapKey)
                .retrieve()
                .body(PlaceResponse.class);

        return placeResponse != null ? placeResponse.getPlaces() : List.of();
    }

    LocationDto locationForPlaceId(String placeId) throws JsonProcessingException {
        String response = restClient
                .get()
                .uri("https://maps.googleapis.com/maps/api/place/details/json?place_id=" + placeId +
                        "&types=geocode" +
                        "&key=" + googleMapKey)
                .retrieve()
                .body(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(response);
        JsonNode location = json.path("result").path("geometry").path("location");

        double lat = location.path("lat").asDouble();
        double lng = location.path("lng").asDouble();

        return new LocationDto(lat, lng);
    }

    public String getRoute(double originLat, double originLng, double destLat, double destLng) {
        String uri = String.format(
                "https://maps.googleapis.com/maps/api/directions/json?origin=%s,%s&destination=%s,%s&key=%s",
                originLat, originLng, destLat, destLng, googleMapKey);

        String response = restClient
                .get()
                .uri(uri)
                .retrieve()
                .body(String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode routesArray = jsonResponse.path("routes");
            if (routesArray.isArray() && !routesArray.isEmpty()) {
                // Extracting the overview_polyline.points
                JsonNode overviewPolyline = routesArray.get(0).path("overview_polyline");
                return overviewPolyline.path("points").asText();

            } else {
                log.error("No routes found");
                return null; // or throw an exception based on your use case
            }
        } catch (Exception e) {
            log.error("Failed to parse JSON response", e);
            return null; // Handle exception based on your use case
        }
    }

}

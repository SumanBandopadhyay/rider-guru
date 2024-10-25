package com.riderguru.rider_guru.map.internal;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

    LocationDto locationForPlaceId(String placeId) throws JSONException {
        String response = restClient
                .get()
                .uri("https://maps.googleapis.com/maps/api/place/details/json?place_id=" + placeId +
                        "&types=geocode" +
                        "&key=" + googleMapKey)
                .retrieve()
                .body(String.class);
        JSONObject json = new JSONObject(response);
        JSONObject location = json.getJSONObject("result")
                .getJSONObject("geometry")
                .getJSONObject("location");

        double lat = location.getDouble("lat");
        double lng = location.getDouble("lng");

        return new LocationDto(lat, lng);
    }

}

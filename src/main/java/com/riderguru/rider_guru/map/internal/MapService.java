package com.riderguru.rider_guru.map.internal;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
class MapService {

    private final MapAdapter mapAdapter;

    MapService(MapAdapter mapAdapter) {
        this.mapAdapter = mapAdapter;
    }
    
    List<PlaceDto> searchPlace(String searchKeyword) {
        return mapAdapter.searchPlace(searchKeyword);
    }

    LocationDto locationForPlaceId(String placeId){
        try {
            return mapAdapter.locationForPlaceId(placeId);
        } catch (Exception e) {
            throw new RuntimeException("Lat-Long not found for placeId : " + placeId, e);
        }
    }

    String getRoute(double originLat, double originLng, double destLat, double destLng) {
        return mapAdapter.getRoute(originLat, originLng, destLat, destLng);
    }
}

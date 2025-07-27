package com.riderguru.rider_guru.map.internal;

import com.riderguru.rider_guru.map.LocationDto;
import com.riderguru.rider_guru.map.PlaceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapServiceTest {

    @Mock
    private MapAdapter mapAdapter;

    @InjectMocks
    private MapService mapService;

    @Test
    void searchPlaceDelegatesToAdapter() {
        List<PlaceDto> places = List.of(new PlaceDto("desc", "pid"));
        when(mapAdapter.searchPlace("foo")).thenReturn(places);

        List<PlaceDto> result = mapService.searchPlace("foo");
        assertEquals(places, result);
    }

    @Test
    void locationForPlaceIdWrapsException() throws Exception {
        when(mapAdapter.locationForPlaceId("pid")).thenThrow(new RuntimeException("err"));
        assertThrows(RuntimeException.class, () -> mapService.locationForPlaceId("pid"));
    }

    @Test
    void getRouteDelegatesToAdapter() {
        when(mapAdapter.getRoute(1.0,2.0,3.0,4.0)).thenReturn("route");
        String result = mapService.getRoute(1.0,2.0,3.0,4.0);
        assertEquals("route", result);
    }
}

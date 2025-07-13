package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.itinerary.ItineraryAPI;
import com.riderguru.rider_guru.itinerary.ItineraryDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin("*")
public class ItineraryGatewayHandler {

    private final ItineraryAPI itineraryAPI;

    public ItineraryGatewayHandler(ItineraryAPI itineraryAPI) {
        this.itineraryAPI = itineraryAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<ItineraryDto> createItinerary(@Valid @RequestBody ItineraryDto itineraryDto) {
        return itineraryAPI.create(itineraryDto);
    }

    @GetMapping("/query")
    public ResponseEntity<List<ItineraryDto>> queryItinerary(@RequestParam(required = false) Map<String, String> params) {
        return itineraryAPI.query(params);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItineraryDto>> getAllItinerary() {
        return itineraryAPI.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ItineraryDto> deleteItinerary(@RequestParam("itinerary-id") Long itineraryId) {
        return itineraryAPI.delete(itineraryId);
    }
}

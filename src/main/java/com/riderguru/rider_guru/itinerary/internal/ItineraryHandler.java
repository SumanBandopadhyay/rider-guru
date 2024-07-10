package com.riderguru.rider_guru.itinerary.internal;

import com.riderguru.rider_guru.itinerary.ItineraryAPI;
import com.riderguru.rider_guru.itinerary.ItineraryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
class ItineraryHandler implements ItineraryAPI {

    private final ItineraryService itineraryService;
    private final ItineraryMapper itineraryMapper;

    public ItineraryHandler(ItineraryService itineraryService, ItineraryMapper itineraryMapper) {
        this.itineraryService = itineraryService;
        this.itineraryMapper = itineraryMapper;
    }

    @Override
    public ResponseEntity<ItineraryDto> create(ItineraryDto ItineraryDto) {
        return ResponseEntity.ok(itineraryMapper.toDto(itineraryMapper.toEntity(ItineraryDto)));
    }

    @Override
    public ResponseEntity<ItineraryDto> getById(Long id) {
        return itineraryService.getById(id)
                .map(itinerary -> ResponseEntity.ok(itineraryMapper.toDto(itinerary)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<ItineraryDto>> getAll() {
        return ResponseEntity.ok(itineraryService.getAll()
                .stream()
                .map(itineraryMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<ItineraryDto> delete(Long id) {
        return itineraryService.getById(id)
                .map(itinerary -> {
                    itineraryService.delete(id);
                    return ResponseEntity.ok(itineraryMapper.toDto(itinerary));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<ItineraryDto>> query(Map<String, String> params) {
        return ResponseEntity.ok(itineraryService.query(params)
                .stream()
                .map(itineraryMapper::toDto)
                .toList());
    }
}

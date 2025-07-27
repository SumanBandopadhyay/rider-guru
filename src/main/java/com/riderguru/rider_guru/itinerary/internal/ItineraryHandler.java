package com.riderguru.rider_guru.itinerary.internal;

import com.riderguru.rider_guru.itinerary.ItineraryAPI;
import com.riderguru.rider_guru.itinerary.ItineraryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Internal handler that implements the {@link ItineraryAPI}.
 * <p>
 * This component translates incoming DTO objects into service calls and
 * logs each operation to aid debugging of itinerary related requests.
 * </p>
 */
@Slf4j
@Component
class ItineraryHandler implements ItineraryAPI {

    private final ItineraryService itineraryService;
    private final ItineraryMapper itineraryMapper;

    public ItineraryHandler(ItineraryService itineraryService, ItineraryMapper itineraryMapper) {
        this.itineraryService = itineraryService;
        this.itineraryMapper = itineraryMapper;
    }

    @Override
    public ResponseEntity<ItineraryDto> create(ItineraryDto itineraryDto) {
        log.info("Creating itinerary with description: {}", itineraryDto.getEventDescription());
        itineraryDto.setIsActive(true);
        ResponseEntity<ItineraryDto> response = ResponseEntity.ok(
                itineraryMapper.toDto(itineraryService.save(itineraryMapper.toEntity(itineraryDto))));
        log.info("Itinerary created with id: {}", response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @Override
    public ResponseEntity<ItineraryDto> getById(Long id) {
        log.info("Fetching itinerary with id: {}", id);
        ResponseEntity<ItineraryDto> response = itineraryService.getById(id)
                .map(itinerary -> ResponseEntity.ok(itineraryMapper.toDto(itinerary)))
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Fetch itinerary id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<ItineraryDto>> getAll() {
        log.info("Fetching all itineraries");
        ResponseEntity<List<ItineraryDto>> response = ResponseEntity.ok(itineraryService.getAll()
                .stream()
                .map(itineraryMapper::toDto)
                .toList());
        log.info("Fetched {} itineraries", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<ItineraryDto> delete(Long id) {
        log.info("Deleting itinerary with id: {}", id);
        ResponseEntity<ItineraryDto> response = itineraryService.getById(id)
                .map(itinerary -> {
                    itineraryService.delete(id);
                    return ResponseEntity.ok(itineraryMapper.toDto(itinerary));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Delete itinerary id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<ItineraryDto>> query(Map<String, String> params) {
        log.info("Query itineraries with params: {}", params);
        ResponseEntity<List<ItineraryDto>> response = ResponseEntity.ok(itineraryService.query(params)
                .stream()
                .map(itineraryMapper::toDto)
                .toList());
        log.info("Query returned {} itineraries", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<ItineraryDto> update(ItineraryDto itineraryDto) {
        return null;
    }
}

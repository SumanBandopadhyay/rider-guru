package com.riderguru.rider_guru.trips.internal;

import com.riderguru.rider_guru.libs.exceptions.GenericException;
import com.riderguru.rider_guru.trips.TripDto;
import com.riderguru.rider_guru.trips.TripsAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Handles internal operations for trips by implementing {@link TripsAPI}.
 * Logs each API method invocation for traceability.
 */
@Slf4j
@Component
class TripHandler implements TripsAPI {

    private final TripService tripService;
    private final TripMapper tripMapper;

    public TripHandler(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }

    @Override
    public ResponseEntity<TripDto> create(TripDto tripDto) {
        log.info("Creating trip titled: {}", tripDto.getTitle());
        tripDto.setIsActive(true);
        ResponseEntity<TripDto> response = ResponseEntity.ok(
                tripMapper.toDto(tripService.save(tripMapper.toEntity(tripDto))));
        log.info("Trip created with id: {}", response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @Override
    public ResponseEntity<TripDto> getById(Long id) {
        log.info("Fetching trip with id: {}", id);
        ResponseEntity<TripDto> response = tripService.getById(id)
                .map(trip -> ResponseEntity.ok(tripMapper.toDto(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Fetch trip id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<TripDto>> getAll() {
        log.info("Fetching all trips");
        ResponseEntity<List<TripDto>> response = ResponseEntity.ok(tripService.getAll()
                .stream()
                .map(tripMapper::toDto)
                .toList());
        log.info("Fetched {} trips", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<TripDto> delete(Long id) {
        log.info("Deleting trip with id: {}", id);
        ResponseEntity<TripDto> response = tripService.getById(id)
                .map(trip -> {
                    tripService.delete(id);
                    return ResponseEntity.ok(tripMapper.toDto(trip));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Delete trip id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<TripDto>> query(Map<String, String> params) {
        log.info("Query trips with params: {}", params);
        ResponseEntity<List<TripDto>> response = ResponseEntity.ok(tripService.query(params)
                .stream()
                .map(tripMapper::toDto)
                .toList());
        log.info("Query returned {} trips", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<TripDto> update(TripDto tripDto) {
        log.info("Updating trip id: {}", tripDto.getId());
        tripService.getById(tripDto.getId())
                .orElseThrow(() -> {
                    String message = "Trip ID " + tripDto.getId() + " not present";
                    log.error(message);
                    return new GenericException(message);
                });
        ResponseEntity<TripDto> response = ResponseEntity.ok(
                tripMapper.toDto(tripService.save(tripMapper.toEntity(tripDto))));
        log.info("Update trip id {} status: {}", tripDto.getId(), response.getStatusCode());
        return response;
    }
}

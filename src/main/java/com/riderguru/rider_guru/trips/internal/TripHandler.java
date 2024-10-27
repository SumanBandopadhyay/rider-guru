package com.riderguru.rider_guru.trips.internal;

import com.riderguru.rider_guru.libs.exceptions.GenericException;
import com.riderguru.rider_guru.trips.TripDto;
import com.riderguru.rider_guru.trips.TripsAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        tripDto.setIsActive(true);
        return ResponseEntity.ok(tripMapper.toDto(tripService.save(tripMapper.toEntity(tripDto))));
    }

    @Override
    public ResponseEntity<TripDto> getById(Long id) {
        return tripService.getById(id)
                .map(trip -> ResponseEntity.ok(tripMapper.toDto(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<TripDto>> getAll() {
        return ResponseEntity.ok(tripService.getAll()
                .stream()
                .map(tripMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<TripDto> delete(Long id) {
        return tripService.getById(id)
                .map(trip -> {
                    tripService.delete(id);
                    return ResponseEntity.ok(tripMapper.toDto(trip));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<TripDto>> query(Map<String, String> params) {
        return ResponseEntity.ok(tripService.query(params)
                .stream()
                .map(tripMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<TripDto> update(TripDto tripDto) {
        tripService.getById(tripDto.getId())
                .orElseThrow(() -> {
                    String message = "Trip ID " + tripDto.getId() + " not present";
                    log.error(message);
                    return new GenericException(message);
                });
        return ResponseEntity.ok(tripMapper.toDto(tripService.save(tripMapper.toEntity(tripDto))));
    }
}

package com.riderguru.rider_guru.trips.internal;

import com.riderguru.rider_guru.libs.GenericService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class TripService implements GenericService<Trip> {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip save(Trip trip) {
        log.info("Save trip : {}", trip.getTitle());
        Trip savedTrip = tripRepository.save(trip);
        log.info("Saved trip id : {}", savedTrip.getId());
        return savedTrip;
    }

    @Override
    public Optional<Trip> getById(Long id) {
        log.info("Trip check for id : {}", id);
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        log.info("Trip found for id {} : {}", id, optionalTrip.isPresent());
        return optionalTrip;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<Trip> getAll() {
        return List.of();
    }

    @Override
    public List<Trip> query(Map<String, String> params) {
        return List.of();
    }
}

package com.riderguru.rider_guru.trips.internal;

import com.riderguru.rider_guru.libs.GenericService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Slf4j
@Service
class TripService implements GenericService<Trip> {

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
        log.info("Delete trip id : {}", id);
        tripRepository.deleteById(id);
        log.info("Delete of trip id : {} complete", id);
    }

    @Override
    public List<Trip> getAll() {
        log.info("Listing all Trip");
        List<Trip> tripList = tripRepository.findAll();
        log.info("Total list size : {}", tripList.size());
        return tripList;
    }

    @Override
    public List<Trip> query(Map<String, String> params) {
        log.info("Trips query : {}", params.toString());
        if (params.isEmpty()) {
            log.info("No criteria provided");
            return Collections.emptyList();
        }
        Specification<Trip> spec = Specification.where(TripSpecification.hasTitle(params.get("title")))
                .and(TripSpecification.hasStartPointName(params.get("startPointName")))
                .and(TripSpecification.hasEndPointName(params.get("endPointName")))
                .and(TripSpecification.hasScheduledStartTime(LocalDateTime.parse(params.get("scheduledStartTime"))))
                .and(TripSpecification.hasActualStartTime(LocalDateTime.parse(params.get("actualStartTime"))))
                .and(TripSpecification.hasScheduledEndTime(LocalDateTime.parse(params.get("scheduledEndTime"))))
                .and(TripSpecification.hasActualEndTime(LocalDateTime.parse(params.get("actualEndTime"))))
                .and(TripSpecification.isActive(Boolean.parseBoolean(params.get("isActive"))));
        List<Trip> trips = tripRepository.findAll(spec);
        log.info("Trips found : {}", trips.size());
        return trips;
    }
}

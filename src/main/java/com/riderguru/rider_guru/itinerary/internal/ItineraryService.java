package com.riderguru.rider_guru.itinerary.internal;

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
public class ItineraryService implements GenericService<Itinerary> {

    private final ItineraryRepository itineraryRepository;

    public ItineraryService(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    @Override
    public Itinerary save(Itinerary itinerary) {
        log.info("Save itinerary : {}", itinerary.getEventDescription());
        Itinerary savedItinerary = itineraryRepository.save(itinerary);
        log.info("Saved itinerary id : {}", itinerary.getId());
        return savedItinerary;
    }

    @Override
    public Optional<Itinerary> getById(Long id) {
        log.info("Itinerary check for id : {}", id);
        Optional<Itinerary> optionalItinerary = itineraryRepository.findById(id);
        log.info("Itinerary found for id {} : {}", id, optionalItinerary.isPresent());
        return optionalItinerary;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Itinerary> getAll() {
        log.info("Listing all itineraries");
        List<Itinerary> itineraryList = itineraryRepository.findAll();
        log.info("Total itinerary count : {}", itineraryList.size());
        return itineraryList;
    }

    @Override
    public List<Itinerary> query(Map<String, String> params) {
        return List.of();
    }
}

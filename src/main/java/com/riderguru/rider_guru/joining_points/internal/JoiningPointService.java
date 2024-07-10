package com.riderguru.rider_guru.joining_points.internal;

import com.riderguru.rider_guru.libs.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
class JoiningPointService implements GenericService<JoiningPoint> {

    private final JoiningPointRepository joiningPointRepository;

    public JoiningPointService(JoiningPointRepository joiningPointRepository) {
        this.joiningPointRepository = joiningPointRepository;
    }

    @Override
    public JoiningPoint save(JoiningPoint joiningPoint) {
        log.info("Save joining point : {}", joiningPoint.getJoiningPointName());
        JoiningPoint savedJoiningPoint = joiningPointRepository.save(joiningPoint);
        log.info("Saved joining point id : {}", savedJoiningPoint.getId());
        return savedJoiningPoint;
    }

    @Override
    public Optional<JoiningPoint> getById(Long id) {
        log.info("Joining date check for id : {}", id);
        Optional<JoiningPoint> optionalJoiningPoint = joiningPointRepository.findById(id);
        log.info("Joining date check for id {} : {}", id, optionalJoiningPoint.isPresent());
        return optionalJoiningPoint;
    }

    @Override
    public void delete(Long id) {
        log.info("Delete joining date id : {}", id);
        joiningPointRepository.deleteById(id);
        log.info("Delete joining date id : {} complete", id);
    }

    @Override
    public List<JoiningPoint> getAll() {
        log.info("Listing joining points");
        List<JoiningPoint> joiningPointList = joiningPointRepository.findAll();
        log.info("Joining points list size :  {}", joiningPointList.size());
        return joiningPointList;
    }

    @Override
    public List<JoiningPoint> query(Map<String, String> params) {
        log.info("Joining points query : {}", params.toString());
        if (params.isEmpty()) {
            log.info("No criteria provided");
            return Collections.emptyList();
        }
        Specification<JoiningPoint> spec = Specification.where(
                        JoiningPointSpecification.hasJoiningPointName(params.get("joiningPointName"))
                )
                .and(JoiningPointSpecification.hasJoiningPointLocMap(params.get("joiningPointLocMap")))
                .and(JoiningPointSpecification.hasScheduledJoiningTime(parseLocalDateTime(params.get("scheduledJoiningTime"))))
                .and(JoiningPointSpecification.hasActualJoiningTime(parseLocalDateTime(params.get("actualJoiningTime"))))
                .and(JoiningPointSpecification.isActive(parseBoolean(params.get("isActive"))))
                .and(JoiningPointSpecification.hasTripId(parseLong(params.get("tripId"))));
        List<JoiningPoint> joiningPoints = joiningPointRepository.findAll(spec);
        log.info("Joining points found : {}", joiningPoints.size());
        return joiningPoints;
    }

    private LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return StringUtils.hasText(dateTimeStr) ? LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME) : null;
    }

    private Boolean parseBoolean(String booleanStr) {
        return StringUtils.hasText(booleanStr) ? Boolean.parseBoolean(booleanStr) : null;
    }

    private Long parseLong(String longStr) {
        return StringUtils.hasText(longStr) ? Long.parseLong(longStr) : null;
    }
}

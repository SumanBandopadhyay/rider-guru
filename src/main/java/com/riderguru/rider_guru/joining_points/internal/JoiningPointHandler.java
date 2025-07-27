package com.riderguru.rider_guru.joining_points.internal;

import com.riderguru.rider_guru.joining_points.JoiningPointsAPI;
import com.riderguru.rider_guru.joining_points.JoiningPointsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Internal handler for {@link JoiningPointsAPI} operations.
 * <p>
 * Handles mapping between DTOs and the service layer while logging each
 * request for easy troubleshooting.
 * </p>
 */
@Slf4j
@Component
class JoiningPointHandler implements JoiningPointsAPI {

    private final JoiningPointService joiningPointService;
    private final JoiningPointMapper joiningPointMapper;

    public JoiningPointHandler(JoiningPointService joiningPointService, JoiningPointMapper joiningPointMapper) {
        this.joiningPointService = joiningPointService;
        this.joiningPointMapper = joiningPointMapper;
    }

    @Override
    public ResponseEntity<JoiningPointsDto> create(JoiningPointsDto joiningPointsDto) {
        log.info("Creating joining point with name: {}", joiningPointsDto.getNodalPointName());
        joiningPointsDto.setIsActive(true);
        ResponseEntity<JoiningPointsDto> response = ResponseEntity.ok(
                joiningPointMapper.toDto(joiningPointService.save(joiningPointMapper.toEntity(joiningPointsDto))));
        log.info("Joining point created with id: {}", response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @Override
    public ResponseEntity<JoiningPointsDto> getById(Long id) {
        log.info("Fetching joining point with id: {}", id);
        ResponseEntity<JoiningPointsDto> response = joiningPointService.getById(id)
                .map(joiningPoint -> ResponseEntity.ok(joiningPointMapper.toDto(joiningPoint)))
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Fetch joining point id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<JoiningPointsDto>> getAll() {
        log.info("Fetching all joining points");
        ResponseEntity<List<JoiningPointsDto>> response = ResponseEntity.ok(joiningPointService.getAll()
                .stream()
                .map(joiningPointMapper::toDto)
                .toList());
        log.info("Fetched {} joining points", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<JoiningPointsDto> delete(Long id) {
        log.info("Deleting joining point with id: {}", id);
        ResponseEntity<JoiningPointsDto> response = joiningPointService.getById(id)
                .map(joiningPoint -> {
                    joiningPointService.delete(id);
                    return ResponseEntity.ok(joiningPointMapper.toDto(joiningPoint));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        log.info("Delete joining point id {} status: {}", id, response.getStatusCode());
        return response;
    }

    @Override
    public ResponseEntity<List<JoiningPointsDto>> query(Map<String, String> params) {
        log.info("Query joining points with params: {}", params);
        ResponseEntity<List<JoiningPointsDto>> response = ResponseEntity.ok(joiningPointService.query(params)
                .stream()
                .map(joiningPointMapper::toDto)
                .toList());
        log.info("Query returned {} joining points", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @Override
    public ResponseEntity<JoiningPointsDto> update(JoiningPointsDto joiningPointsDto) {
        return null;
    }
}

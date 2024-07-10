package com.riderguru.rider_guru.joining_points.internal;

import com.riderguru.rider_guru.joining_points.JoiningPointsAPI;
import com.riderguru.rider_guru.joining_points.JoiningPointsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        return ResponseEntity.ok(joiningPointMapper.toDto(joiningPointService.save(joiningPointMapper.toEntity(joiningPointsDto))));
    }

    @Override
    public ResponseEntity<JoiningPointsDto> getById(Long id) {
        return joiningPointService.getById(id)
                .map(joiningPoint -> ResponseEntity.ok(joiningPointMapper.toDto(joiningPoint)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<JoiningPointsDto>> getAll() {
        return ResponseEntity.ok(joiningPointService.getAll()
                .stream()
                .map(joiningPointMapper::toDto)
                .toList());
    }

    @Override
    public ResponseEntity<JoiningPointsDto> delete(Long id) {
        return joiningPointService.getById(id)
                .map(joiningPoint -> {
                    joiningPointService.delete(id);
                    return ResponseEntity.ok(joiningPointMapper.toDto(joiningPoint));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<JoiningPointsDto>> query(Map<String, String> params) {
        return ResponseEntity.ok(joiningPointService.query(params)
                .stream()
                .map(joiningPointMapper::toDto)
                .toList());
    }
}

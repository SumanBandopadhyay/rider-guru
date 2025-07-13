package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.joining_points.JoiningPointsAPI;
import com.riderguru.rider_guru.joining_points.JoiningPointsDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/joining_points")
@CrossOrigin("*")
public class JoiningPointsGatewayHandler {

    private final JoiningPointsAPI joiningPointsAPI;

    public JoiningPointsGatewayHandler(JoiningPointsAPI joiningPointsAPI) {
        this.joiningPointsAPI = joiningPointsAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<JoiningPointsDto> createJoiningPoints(@Valid @RequestBody JoiningPointsDto joiningPointsDto) {
        return joiningPointsAPI.create(joiningPointsDto);
    }

    @GetMapping("/query")
    public ResponseEntity<List<JoiningPointsDto>> queryJoiningPoints(@RequestParam(required = false) Map<String, String> params) {
        return joiningPointsAPI.query(params);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JoiningPointsDto>> getAllJoiningPoints() {
        return joiningPointsAPI.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<JoiningPointsDto> deleteJoiningPoints(@RequestParam("joining-point-id") Long joiningPointId) {
        return joiningPointsAPI.delete(joiningPointId);
    }
}

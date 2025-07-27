package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.joining_points.JoiningPointsAPI;
import com.riderguru.rider_guru.joining_points.JoiningPointsDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Gateway controller for managing joining points.
 *
 * <p>Exposes CRUD operations for joining points and logs requests/responses
 * for easier diagnostics.</p>
 */
@RestController
@RequestMapping("/api/joining_points")
@CrossOrigin("*")
public class JoiningPointsGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(JoiningPointsGatewayHandler.class);

    private final JoiningPointsAPI joiningPointsAPI;

    public JoiningPointsGatewayHandler(JoiningPointsAPI joiningPointsAPI) {
        this.joiningPointsAPI = joiningPointsAPI;
    }

    @PostMapping("/create")
    public ResponseEntity<JoiningPointsDto> createJoiningPoints(@Valid @RequestBody JoiningPointsDto joiningPointsDto) {
        logger.info("Creating joining point for tripId: {}", joiningPointsDto.getTripId());
        ResponseEntity<JoiningPointsDto> response = joiningPointsAPI.create(joiningPointsDto);
        logger.info("Joining point creation finished with status: {} and returned id: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().getId() : null);
        return response;
    }

    @GetMapping("/query")
    public ResponseEntity<List<JoiningPointsDto>> queryJoiningPoints(@RequestParam(required = false) Map<String, String> params) {
        logger.info("Querying joining points with params: {}", params);
        ResponseEntity<List<JoiningPointsDto>> response = joiningPointsAPI.query(params);
        logger.info("Joining point query finished with status: {} and result count: {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JoiningPointsDto>> getAllJoiningPoints() {
        logger.info("Fetching all joining points");
        ResponseEntity<List<JoiningPointsDto>> response = joiningPointsAPI.getAll();
        logger.info("Fetched {} joining points", response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteJoiningPoints(@RequestParam("joining-point-id") Long joiningPointId) {
        logger.info("Deleting joining point with id: {}", joiningPointId);
        ResponseEntity<Void> response = joiningPointsAPI.delete(joiningPointId);
        logger.info("Joining point deletion finished with status: {}", response.getStatusCode());
        return response;
    }
}

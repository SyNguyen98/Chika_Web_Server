package com.chika.server.controllers;

import com.chika.server.models.product.Sensor;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.products.SensorResponseForAdmin;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.product.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Sensor requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 28-05-2020
 */
@RestController
@RequestMapping("sensor")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<SensorResponseForAdmin> getAll() {
        return sensorService.getAll().stream()
                .map(SensorResponseForAdmin::new)
                .collect(Collectors.toList());
    }

    @GetMapping("user")
    public List<Sensor> getAllByUserId(@CurrentUser UserPrincipal currentUser) {
        return sensorService.getAllByUserId(currentUser.getId());
    }

    @GetMapping("/{id}")
    public Sensor getById(@PathVariable String id) {
        return sensorService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SensorResponseForAdmin save(@RequestBody Sensor sensor) {
        return new SensorResponseForAdmin(sensorService.save(sensor));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/user")
    public Sensor updateUser(@RequestBody Sensor sensor) {
        return sensorService.updateUser(sensor.getId(), sensor.getUserId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSensor(@PathVariable String id) {
        sensorService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Sensor has been deleted"));
    }
}

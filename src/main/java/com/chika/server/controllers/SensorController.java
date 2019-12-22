package com.chika.server.controllers;

import com.chika.server.models.house.Sensor;
import com.chika.server.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Sensor requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<Sensor> getAll() {
        return sensorService.getAll();
    }

    @GetMapping("/{id}")
    public Sensor getById(@PathVariable String id) {
        return sensorService.getById(id);
    }

    @PostMapping
    public Sensor save(@RequestBody Sensor sensor) {
        return sensorService.save(sensor);
    }

    @PutMapping
    public Sensor updateInfo(@RequestBody Sensor sensor) {
        return sensorService.updateInfo(sensor.getId(), sensor.getName(), sensor.getData());
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable String id) {
        sensorService.deleteById(id);
    }
}

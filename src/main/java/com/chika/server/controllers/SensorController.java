package com.chika.server.controllers;

import com.chika.server.models.house.Sensor;
import com.chika.server.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.findAllSensors();
    }

    @GetMapping("/{id}")
    public Sensor getSensorById(@PathVariable String id) {
        return sensorService.findSensorById(id);
    }

    @PostMapping
    public Sensor saveSensor(@RequestBody Sensor sensor) {
        return sensorService.saveSensor(sensor);
    }

    @PutMapping
    public Sensor updateSensor(@RequestBody Sensor sensor) {
        return sensorService.updateSensor(sensor.getId(), sensor.getName(), sensor.getData());
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable String id) {
        sensorService.deleteSensor(id);
    }
}

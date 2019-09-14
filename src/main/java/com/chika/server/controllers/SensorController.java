package com.chika.server.controllers;

import com.chika.server.models.house.Sensor;
import com.chika.server.models.house.SensorHistory;
import com.chika.server.services.SensorHistoryService;
import com.chika.server.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private SensorHistoryService sensorHistoryService;

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

    @PutMapping("/{id}/{data}")
    public Sensor updateSensor(@PathVariable(value = "id") String id, @PathVariable(value = "data") double data) {
        SensorHistory sensorHistory = new SensorHistory(id, data, new Timestamp(System.currentTimeMillis()));
        sensorHistoryService.saveSensor(sensorHistory);
        return sensorService.updateSensor(id, data);
    }

    @DeleteMapping("/{id}")
    public String deleteSensor(@PathVariable String id) {
        return sensorService.deleteSensor(id);
    }
}

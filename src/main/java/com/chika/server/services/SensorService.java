package com.chika.server.services;

import com.chika.server.models.house.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {

    Sensor findSensorById(String id);

    List<Sensor> findAllSensors();

    Sensor saveSensor(Sensor sensor);

    Sensor updateSensor(String id, double data);

    String deleteSensor(String id);
}

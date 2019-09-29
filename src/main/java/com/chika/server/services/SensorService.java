package com.chika.server.services;

import com.chika.server.models.house.Sensor;
import com.chika.server.models.house.SensorHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {

    Sensor findSensorById(String id);

    List<Sensor> findAllSensors();

    Sensor saveSensor(Sensor sensor);

    Sensor updateSensor(String id, String name, double data);

    void deleteSensor(String id);

    List<SensorHistory> findSensorHistoryById(String id);

    List<SensorHistory> findAllSensorHistories();

    SensorHistory saveSensorHistory(SensorHistory sensor);

    Boolean deleteSensorHistory(String id);
}

package com.chika.server.services;

import com.chika.server.models.house.SensorHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorHistoryService {

    List<SensorHistory> findSensorsById(String id);

    List<SensorHistory> findAllSensors();

    SensorHistory saveSensor(SensorHistory sensor);

    Boolean deleteSensor(String id);
}

package com.chika.server.services.implement;

import com.chika.server.models.house.SensorHistory;
import com.chika.server.repositories.SensorHistoryRepository;
import com.chika.server.services.SensorHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorHistoryServiceImpl implements SensorHistoryService {

    @Autowired
    private SensorHistoryRepository sensorHistoryRepository;

    @Override
    public List<SensorHistory> findSensorsById(String id) {
        return null;
    }

    @Override
    public List<SensorHistory> findAllSensors() {
        return null;
    }

    @Override
    public SensorHistory saveSensor(SensorHistory sensor) {
        return sensorHistoryRepository.save(sensor);
    }

    @Override
    public Boolean deleteSensor(String id) {
        return null;
    }
}

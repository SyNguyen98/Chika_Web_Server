package com.chika.server.services.implement;

import com.chika.server.models.house.Sensor;
import com.chika.server.models.house.SensorHistory;
import com.chika.server.repositories.SensorHistoryRepository;
import com.chika.server.repositories.SensorRepository;
import com.chika.server.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-07-2019
 */
@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorHistoryRepository sensorHistoryRepository;

    @Override
    public Sensor findSensorById(String id) {
        if (sensorRepository.findById(id).isPresent()) {
            return sensorRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Sensor> findAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor updateSensor(String id, double data) {
        if (sensorRepository.findById(id).isPresent()) {
            Sensor sensor = sensorRepository.findById(id).get();
            sensor.setData(data);
            return sensorRepository.save(sensor);
        }
        return null;
    }

    @Override
    public String deleteSensor(String id) {
        if (sensorRepository.findById(id).isPresent()) {
            Sensor sensor = sensorRepository.findById(id).get();
            sensorRepository.delete(sensor);
            return "deleted";
        }
        return null;
    }

    @Override
    public List<SensorHistory> findSensorHistoryById(String id) {
        return null;
    }

    @Override
    public List<SensorHistory> findAllSensorHistories() {
        return null;
    }

    @Override
    public SensorHistory saveSensorHistory(SensorHistory sensor) {
        return  sensorHistoryRepository.save(sensor);
    }

    @Override
    public Boolean deleteSensorHistory(String id) {
        return null;
    }
}

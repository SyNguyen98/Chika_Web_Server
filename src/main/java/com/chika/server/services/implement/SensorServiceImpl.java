package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.SensorHistory;
import com.chika.server.models.product.Sensor;
import com.chika.server.repositories.house.SensorHistoryRepository;
import com.chika.server.repositories.product.SensorRepository;
import com.chika.server.services.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 21-02-2020
 */
@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    private final SensorHistoryRepository sensorHistoryRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, SensorHistoryRepository sensorHistoryRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorHistoryRepository = sensorHistoryRepository;
    }

    @Override
    public List<Sensor> getAll() {
        return sensorRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Sensor getById(String id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor", "id", id));
    }

    @Override
    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor updateUser(String id, Long userId) {
        Sensor sensor = getById(id);
        sensor.setUserId(userId);
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteById(String id) {
        Sensor sensor = getById(id);
        sensorRepository.delete(sensor);
    }

    @Override
    public List<SensorHistory> getAllHistoriesBySensorId(String id) {
        return null;
    }

    @Override
    public SensorHistory saveHistory(SensorHistory sensor) {
        return  sensorHistoryRepository.save(sensor);
    }

    @Override
    public void deleteHistory(String id) {

    }
}

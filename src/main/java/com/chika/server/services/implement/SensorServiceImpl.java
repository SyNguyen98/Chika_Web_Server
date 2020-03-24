package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.Sensor;
import com.chika.server.repositories.product.SensorRepository;
import com.chika.server.services.product.SensorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 24-03-2020
 */
@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    @Transactional
    public List<Sensor> getAll() {
        return sensorRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional
    public List<Sensor> getAllByUserId(Long userId) {
        return sensorRepository.findAllByUserId(userId);
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
    public long countAll() {
        return sensorRepository.count();
    }

    @Override
    public Boolean hasOwner(String id) {
        return getById(id).getUserId() != null;
    }
}

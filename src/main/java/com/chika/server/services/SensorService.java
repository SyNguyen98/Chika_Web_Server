package com.chika.server.services;

import com.chika.server.models.house.SensorHistory;
import com.chika.server.models.product.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {

    List<Sensor> getAll();

    Sensor getById(String id);

    Sensor save(Sensor sensor);

    Sensor updateUser(String id, Long userId);

    void deleteById(String id);

    List<SensorHistory> getAllHistoriesBySensorId(String id);

    SensorHistory saveHistory(SensorHistory sensor);

    void deleteHistory(String id);
}

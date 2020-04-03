package com.chika.server.services.product;

import com.chika.server.models.product.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {

    List<Sensor> getAll();

    List<Sensor> getAllByUserId(Long userId);

    Sensor getById(String id);

    Sensor save(Sensor sensor);

    Sensor updateUser(String id, Long userId);

    long countAll();

    long countByUserId(Long userId);

    void deleteById(String id);

    Boolean hasOwner(String id);
}

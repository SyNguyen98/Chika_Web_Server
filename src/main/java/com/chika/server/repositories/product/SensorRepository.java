package com.chika.server.repositories.product;

import com.chika.server.models.product.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 03-04-2020
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {

    List<Sensor> findAllByOrderByCreatedAtDesc();

    List<Sensor> findAllByUserId(Long userId);

    long countAllByUserId(Long userId);
}

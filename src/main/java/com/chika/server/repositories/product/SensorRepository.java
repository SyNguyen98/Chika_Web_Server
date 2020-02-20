package com.chika.server.repositories.product;

import com.chika.server.models.product.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-02-2020
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {

}

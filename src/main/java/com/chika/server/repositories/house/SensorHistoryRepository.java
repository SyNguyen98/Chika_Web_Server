package com.chika.server.repositories.house;

import com.chika.server.models.house.SensorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-07-2019
 */
@Repository
public interface SensorHistoryRepository extends JpaRepository<SensorHistory, String> {

}

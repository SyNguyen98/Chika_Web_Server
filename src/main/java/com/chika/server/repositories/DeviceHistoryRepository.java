package com.chika.server.repositories;

import com.chika.server.models.house.DeviceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-10-2019
 */
@Repository
public interface DeviceHistoryRepository extends JpaRepository<DeviceHistory, String> {

    List<DeviceHistory> findAllByDeviceId(String deviceId);
}

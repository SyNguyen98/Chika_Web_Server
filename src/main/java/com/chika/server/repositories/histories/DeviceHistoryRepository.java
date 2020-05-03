package com.chika.server.repositories.histories;

import com.chika.server.models.histories.DeviceHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceHistoryRepository extends JpaRepository<DeviceHistory, String> {

    List<DeviceHistory> findAllByDeviceId(String deviceId, Pageable pageable);
}

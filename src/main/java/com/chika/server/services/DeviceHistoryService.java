package com.chika.server.services;

import com.chika.server.models.house.DeviceHistory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface DeviceHistoryService {

    List<DeviceHistory> findDevicesById(String id);

    List<DeviceHistory> findAllDevices();

    DeviceHistory saveDevice(DeviceHistory device);

    Boolean deleteDevice(String id);
}

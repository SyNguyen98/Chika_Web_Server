package com.chika.server.services.implement;

import com.chika.server.models.house.DeviceHistory;
import com.chika.server.repositories.DeviceHistoryRepository;
import com.chika.server.services.DeviceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-08-2019
 */
@Service
public class DeviceHistoryServiceImpl implements DeviceHistoryService {

    @Autowired
    private DeviceHistoryRepository deviceHistoryRepository;

    @Override
    public List<DeviceHistory> findDevicesById(String id) {
        return deviceHistoryRepository.findDeviceHistoriesById(id);
    }

    @Override
    public List<DeviceHistory> findAllDevices() {
        return deviceHistoryRepository.findAll();
    }

    @Override
    public DeviceHistory saveDevice(DeviceHistory device) {
        return deviceHistoryRepository.save(device);
    }

    @Override
    public Boolean deleteDevice(String id) {
        return null;
    }
}

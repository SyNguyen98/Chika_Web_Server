package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.repositories.DeviceHistoryRepository;
import com.chika.server.repositories.DeviceRepository;
import com.chika.server.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-07-2019
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceHistoryRepository deviceHistoryRepository;

    @Override
    public List<Device> getAllByRoomId(String roomId) {
        return deviceRepository.findAllByRoomId(roomId);
    }

    @Override
    public List<Device> getAllBySwitchId(String switchId) {
        return deviceRepository.findAllBySwitchId(switchId);
    }

    @Override
    public List<Device> getAllByUserId(Long userId) {
        return deviceRepository.findAllByUserId(userId);
    }

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(String id, String name, int state) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device", "id", id));
        device.setName(name);
        device.setState(state);
        return deviceRepository.save(device);
    }

    @Override
    public String deleteDevice(String id) {
        deviceRepository.deleteById(id);
        return null;
    }

    @Override
    public List<DeviceHistory> getHistoriesByDeviceId(String id) {
        return deviceHistoryRepository.findAllByDeviceId(id);
    }

    @Override
    public List<DeviceHistory> getAllHistories() {
        return deviceHistoryRepository.findAll();
    }

    @Override
    public DeviceHistory saveHistory(String deviceId, int state) {
        DeviceHistory deviceHistory = new DeviceHistory(deviceId, state, new Timestamp(System.currentTimeMillis()));
        return deviceHistoryRepository.save(deviceHistory);
    }

    @Override
    public Boolean deleteHistory(String id) {
        return null;
    }
}

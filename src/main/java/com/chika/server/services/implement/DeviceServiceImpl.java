package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.repositories.house.DeviceHistoryRepository;
import com.chika.server.repositories.house.DeviceRepository;
import com.chika.server.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Manipulating data in the Device table
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-11-2019
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
    public Device getById(String id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device", "id", id));
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device updateInfo(String id, String name, String roomId, Long userId) {
        Device device = getById(id);
        device.setName(name);
        device.setRoomId(roomId);
        device.setUserId(userId);
        return deviceRepository.save(device);
    }

    @Override
    public Device updateState(String id, int state) {
        Device device = getById(id);
        device.setState(state);
        return deviceRepository.save(device);
    }

    @Override
    public void delete(String id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return getById(id).getUserId().equals(userId);
    }

    @Override
    public List<DeviceHistory> getAllHistoriesByDeviceId(String id) {
        return deviceHistoryRepository.findAllByDeviceId(id);
    }

    @Override
    public DeviceHistory saveHistory(String deviceId, int state) {
        DeviceHistory deviceHistory = new DeviceHistory(deviceId, state, new Timestamp(System.currentTimeMillis()));
        return deviceHistoryRepository.save(deviceHistory);
    }

    @Override
    public void deleteAllHistoriesByDeviceId(String deviceId) {
        deviceHistoryRepository.deleteAllByDeviceId(deviceId);
    }
}

package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.repositories.DeviceHistoryRepository;
import com.chika.server.repositories.DeviceRepository;
import com.chika.server.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Device> getDevicesByRoomId(String roomId) {
        return deviceRepository.findAllByRoomId(roomId);
    }

    @Override
    public List<Device> getDevicesBySwitchId(String switchId) {
        return deviceRepository.findAllBySwitchId(switchId);
    }

    @Override
    public List<Device> getDevicesByUserId(Long userId) {
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
        if (deviceRepository.findById(id).isPresent()) {
            Device device = deviceRepository.findById(id).get();
            deviceRepository.delete(device);
            return "deleted";
        }
        return null;
    }

    @Override
    public List<DeviceHistory> findDeviceHistoriesById(String id) {
        return deviceHistoryRepository.findDeviceHistoriesById(id);
    }

    @Override
    public List<DeviceHistory> findAllDeviceHistories() {
        return deviceHistoryRepository.findAll();
    }

    @Override
    public DeviceHistory saveDeviceHistory(DeviceHistory device) {
        return deviceHistoryRepository.save(device);
    }

    @Override
    public Boolean deleteDeviceHistory(String id) {
        return null;
    }
}

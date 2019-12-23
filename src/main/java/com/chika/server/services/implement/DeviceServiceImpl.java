package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.repositories.house.DeviceHistoryRepository;
import com.chika.server.repositories.house.DeviceRepository;
import com.chika.server.repositories.house.RoomRepository;
import com.chika.server.services.DeviceService;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * CRUD functions for Device
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    private final DeviceHistoryRepository deviceHistoryRepository;

    private final RoomService roomService;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceHistoryRepository deviceHistoryRepository, RoomService roomService) {
        this.deviceRepository = deviceRepository;
        this.deviceHistoryRepository = deviceHistoryRepository;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    public List<Device> getAllByRoomId(String roomId) {
        return deviceRepository.findAllByRoomId(roomId);
    }

    @Override
    @Transactional
    public List<Device> getAllBySwitchId(String switchId) {
        return deviceRepository.findAllBySwitchId(switchId);
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
    public Device updateInfo(String id, String name, String roomId) {
        Device device = getById(id);
        device.setName(name);
        device.setRoomId(roomId);
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
        return roomService.isOwner(getById(id).getRoomId(), userId);
    }

    @Override
    public List<DeviceHistory> getAllHistoriesByDeviceId(String id) {
        return deviceHistoryRepository.findAllByDeviceId(id);
    }

    @Override
    public DeviceHistory saveHistory(DeviceHistory deviceHistory) {
        return deviceHistoryRepository.save(deviceHistory);
    }

    @Override
    public void deleteAllHistoriesByDeviceId(String deviceId) {
        deviceHistoryRepository.deleteAllByDeviceId(deviceId);
    }
}

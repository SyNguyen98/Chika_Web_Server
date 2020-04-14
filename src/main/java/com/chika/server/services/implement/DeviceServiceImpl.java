package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Device;
import com.chika.server.repositories.house.DeviceRepository;
import com.chika.server.services.DeviceService;
import com.chika.server.services.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CRUD functions for Device
 * @author Sy Nguyen
 * @version 1.0
 * @since 14-04-2020
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    private final RoomService roomService;

    public DeviceServiceImpl(DeviceRepository deviceRepository, RoomService roomService) {
        this.deviceRepository = deviceRepository;
        this.roomService = roomService;
    }

    @Override
    public List<Device> getAllByRoomId(String roomId) {
        return deviceRepository.findAllByRoomIdOrderByCreatedAt(roomId);
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
    public Device updateInfoById(String id, String logo, String name) {
        Device device = getById(id);
        device.setLogo(logo);
        device.setName(name);
        return deviceRepository.save(device);
    }

    @Override
    public Device updateStateById(String id, boolean state) {
        Device device = getById(id);
        device.setState(state);
        return deviceRepository.save(device);
    }

    @Override
    public void deleteById(String id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return roomService.getById(getById(id).getRoomId())
                .getUserId().equals(userId);
    }

    @Override
    public List<Device> getDeviceByTopic(String topic) {
        return deviceRepository.findAllByTopicContains(topic);
    }
}

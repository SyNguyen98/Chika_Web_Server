package com.chika.server.services;

import com.chika.server.models.house.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> getAllByRoomId(String roomId);

    Device getById(String id);

    Device save(Device device);

    Device updateInfoById(String id, String logo, String name);

    Device updateStateById(String id, int state);

    void deleteById(String id);

    Boolean isOwner(String id, Long userId);
}

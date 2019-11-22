package com.chika.server.services;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> getAllByRoomId(String roomId);

    List<Device> getAllBySwitchId(String switchId);

    List<Device> getAllByUserId(Long userId);

    Device getById(String id);

    Device save(Device device);

    Device updateInfo(String id, String name, String roomId, Long userId);

    Device updateState(String id, int state);

    void delete(String id);

    Boolean isOwner(String id, Long userId);

    // HISTORY

    List<DeviceHistory> getAllHistoriesByDeviceId(String id);

    DeviceHistory saveHistory(String deviceId, int state);

    Boolean deleteHistory(String id);
}

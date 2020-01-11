package com.chika.server.services;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> getAllByRoomId(String roomId);

    List<Device> getAllBySwitchId(String switchId);

    Device getById(String id);

    Device save(Device device);

    Device updateInfo(String id, String name, String roomId);

    Device updateState(String id, int state);

    void delete(String id);

    Boolean isOwner(String id, Long userId);

    // HISTORY

    List<DeviceHistory> getAllHistoriesByDeviceId(String deviceId, int page, int size);

    DeviceHistory saveHistory(DeviceHistory deviceHistory);

    void deleteAllHistoriesByDeviceId(String deviceId);
}

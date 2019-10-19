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

    Device saveDevice(Device device);

    Device updateDevice(String id, String name, int state);

    String deleteDevice(String id);

    List<DeviceHistory> getHistoriesByDeviceId(String id);

    List<DeviceHistory> getAllHistories();

    DeviceHistory saveHistory(String deviceId, int state);

    Boolean deleteHistory(String id);
}

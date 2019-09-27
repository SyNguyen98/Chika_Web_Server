package com.chika.server.services;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> getDevicesByRoomId(String roomId);

    List<Device> getDevicesBySwitchId(String switchId);

    Device saveDevice(Device device);

    String updateDevice(String id, int name);

    String deleteDevice(String id);

    List<DeviceHistory> findDeviceHistoriesById(String id);

    List<DeviceHistory> findAllDeviceHistories();

    DeviceHistory saveDeviceHistory(DeviceHistory device);

    Boolean deleteDeviceHistory(String id);
}

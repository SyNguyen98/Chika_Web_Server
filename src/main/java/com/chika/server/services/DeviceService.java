package com.chika.server.services;

import com.chika.server.models.house.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    Device findDeviceById(String id);

    List<Device> findAllDevices();

    Device saveDevice(Device device);

    String updateDevice(String id, int name);

    String deleteDevice(String id);
}

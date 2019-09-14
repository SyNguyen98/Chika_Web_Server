package com.chika.server.services.implement;

import com.chika.server.models.house.Device;
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

    @Override
    public Device findDeviceById(String id) {
        if (deviceRepository.findById(id).isPresent()) {
            return deviceRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public String updateDevice(String id, int state) {
        if (deviceRepository.findById(id).isPresent()) {
            Device device = deviceRepository.findById(id).get();
            device.setState(state);
            deviceRepository.save(device);
            return "Update succeeded!!! Id: " + id + ", state: " + state;
        }
        return "Update failed";
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
}

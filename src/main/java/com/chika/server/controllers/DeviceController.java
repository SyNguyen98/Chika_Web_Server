package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 27-09-2019
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/room/{roomId}")
    public List<Device> getDevicesByRoomId(@PathVariable String roomId) {
        return deviceService.getDevicesByRoomId(roomId);
    }

    @GetMapping("/switch/{switchId}")
    public List<Device> getDevicesBySwitchId(@PathVariable String switchId) {
        return deviceService.getDevicesBySwitchId(switchId);
    }

    @PostMapping
    public Device saveDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);
    }

    @PutMapping
    public Device updateDevice(@RequestBody Device device) {
        return deviceService.updateDevice(device.getId(), device.getName(), device.getState());
    }

    @DeleteMapping("/{id}")
    public String deleteDevice(@PathVariable String id) {
        return deviceService.deleteDevice(id);
    }
}

package com.chika.server.controllers;

import com.chika.server.models.house.DeviceHistory;
import com.chika.server.services.DeviceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Control, update device's state
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-08-2019
 */
@RestController
@RequestMapping("/device-history")
public class DeviceHistoryController {

    @Autowired
    private DeviceHistoryService deviceHistoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceHistory> getAllDevices() {
        return deviceHistoryService.findAllDevices();
    }

    @GetMapping("/{id}")
    public List<DeviceHistory> getDeviceById(@PathVariable String id) {
        return deviceHistoryService.findDevicesById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteDevice(@PathVariable String id) {
        return null;
    }
}

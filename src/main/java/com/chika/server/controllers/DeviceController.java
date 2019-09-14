package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.DeviceHistoryService;
import com.chika.server.services.DeviceService;
import com.chika.server.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Control all devices in house
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-08-2019
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceHistoryService deviceHistoryService;

    @Autowired
    private HttpService httpService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getAllDevices() {
        return deviceService.findAllDevices();
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable String id) {
        return deviceService.findDeviceById(id);
    }

    @PostMapping
    public Device saveDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);
    }

    @PutMapping("/{id}/{state}")
    public void updateDevice(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "id") String id, @PathVariable(value = "state") int state) {
        System.out.println(currentUser.getHouseIp());

//        httpService.put(currentUser.getHouseIp(), id, state);

        DeviceHistory deviceHistory = new DeviceHistory(id, state, new Timestamp(System.currentTimeMillis()));
        deviceHistoryService.saveDevice(deviceHistory);

        System.out.println(deviceService.updateDevice(id, state));
    }

    @DeleteMapping("/{id}")
    public String deleteDevice(@PathVariable String id) {
        return deviceService.deleteDevice(id);
    }
}

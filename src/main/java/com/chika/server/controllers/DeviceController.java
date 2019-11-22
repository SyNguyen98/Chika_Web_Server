package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-11-2019
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/room/{roomId}")
    public List<Device> getDevicesByRoomId(@PathVariable String roomId) {
        return deviceService.getAllByRoomId(roomId);
    }

    @GetMapping("/switch/{switchId}")
    public List<Device> getDevicesBySwitchId(@PathVariable String switchId) {
        return deviceService.getAllBySwitchId(switchId);
    }

    @GetMapping("/user")
    public List<Device> getDevicesByUserId(@CurrentUser UserPrincipal currentUser) {
        return deviceService.getAllByUserId(currentUser.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Device save(@RequestBody Device device) {
        return deviceService.save(new Device("", 0, "", device.getSwitchId(), (long) 0));
    }

    @PutMapping("/name")
    public Device updateInfo(@CurrentUser UserPrincipal currentUser, @RequestBody Device device) {
        return deviceService.updateInfo(device.getId(), device.getName(), device.getRoomId(), currentUser.getId());
    }

    @PutMapping("/state")
    public Device updateState(@RequestBody Device device) {
        deviceService.saveHistory(device.getId(), device.getState());
        return deviceService.updateState(device.getId(), device.getState());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Boolean deleteDevice(@PathVariable String id) {
        deviceService.delete(id);
        return true;
    }

    @GetMapping("/history")
    public List<DeviceHistory> getAllHistoriesByDeviceId(@RequestParam("deviceId") String deviceId) {
        return deviceService.getAllHistoriesByDeviceId(deviceId);
    }
}

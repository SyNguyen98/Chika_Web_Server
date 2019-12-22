package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * To receive Device requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/room/{roomId}")
    public List<Device> getAllByRoomId(@PathVariable String roomId) {
        return deviceService.getAllByRoomId(roomId);
    }

    @GetMapping("/room/{switchId}")
    public List<Device> getAllBySwitchId(@PathVariable String switchId) {
        return deviceService.getAllBySwitchId(switchId);
    }

    @GetMapping("/{id}")
    public Device getById(@PathVariable String id) {
        return deviceService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{switchId}")
    public Device save(@PathVariable String switchId) {
        return deviceService.save(new Device("", 0, "", switchId));
    }

    @PutMapping("/name")
    public Device updateInfo(@RequestBody Device device) {
        return deviceService.updateInfo(device.getId(), device.getName(), device.getRoomId());
    }

    @PutMapping("/state")
    public Device updateState(@RequestBody Device device) {
        deviceService.saveHistory(new DeviceHistory(device.getId(), device.getState()));
        return deviceService.updateState(device.getId(), device.getState());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable String id) {
        deviceService.deleteAllHistoriesByDeviceId(id);
        deviceService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Device has been deleted"));
    }

    // HISTORY
    @GetMapping("/history/{deviceId}")
    public List<DeviceHistory> getAllHistoriesByDeviceId(@PathVariable String deviceId) {
        return deviceService.getAllHistoriesByDeviceId(deviceId);
    }

    @DeleteMapping("/history/{deviceId}")
    public ResponseEntity<?> deleteAllHistoriesByDeviceId(@PathVariable String deviceId) {
        deviceService.deleteAllHistoriesByDeviceId(deviceId);
        return ResponseEntity.ok(new ApiResponse(true, "All device's histories have been deleted"));
    }
}

package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.house.DeviceHistory;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.DeviceHistoryResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Device requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-02-2020
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

    @GetMapping("/switch/{switchId}")
    public List<Device> getAllBySwitchId(@PathVariable String switchId) {
        return deviceService.getAllBySwitchId(switchId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (deviceService.isOwner(id, currentUser.getId())) {
            return ResponseEntity.ok(deviceService.getById(id));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/info")
    public ResponseEntity<?> updateInfo(@CurrentUser UserPrincipal currentUser, @RequestBody Device device) {
        if (deviceService.isOwner(device.getId(), currentUser.getId())) {
            return ResponseEntity.ok(deviceService.updateInfo(device.getId(), device.getName(), device.getRoomId()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/state")
    public ResponseEntity<?> updateState(@CurrentUser UserPrincipal currentUser, @RequestBody Device device) {
        if (deviceService.isOwner(device.getId(), currentUser.getId())) {
            deviceService.saveHistory(new DeviceHistory(device.getId(), device.getState()));
            return ResponseEntity.ok(deviceService.updateState(device.getId(), device.getState()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
                HttpStatus.BAD_REQUEST);
    }

    // HISTORY
    @GetMapping("/{deviceId}/history")
    public ResponseEntity<?> getAllHistoriesByDeviceId(@CurrentUser UserPrincipal currentUser, @PathVariable String deviceId,
                                            @RequestParam("page") int page, @RequestParam("size") int size) {
        if (deviceService.isOwner(deviceId, currentUser.getId())) {
            return ResponseEntity.ok(deviceService.getAllHistoriesByDeviceId(deviceId, page, size)
                    .stream()
                    .map(DeviceHistoryResponse::new)
                    .collect(Collectors.toList()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{deviceId}/history")
    public ResponseEntity<?> deleteAllHistoriesByDeviceId(@CurrentUser UserPrincipal currentUser, @PathVariable String deviceId) {
        if (deviceService.isOwner(deviceId, currentUser.getId())) {
            deviceService.deleteAllHistoriesByDeviceId(deviceId);
            return ResponseEntity.ok(new ApiResponse(true, "All device's histories have been deleted"));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
                HttpStatus.BAD_REQUEST);
    }
}

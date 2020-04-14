package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.house.DeviceResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.DeviceService;
import com.chika.server.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    private final RoomService roomService;

    public DeviceController(DeviceService deviceService, RoomService roomService) {
        this.deviceService = deviceService;
        this.roomService = roomService;
    }

    @GetMapping("/room_id/{roomId}")
    public ResponseEntity<?> getAllByRoomId(@CurrentUser UserPrincipal currentUser, @PathVariable String roomId) {
        if (roomService.isOwner(roomId, currentUser.getId())) {
            return ResponseEntity.ok(deviceService.getAllByRoomId(roomId));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not room's owner"),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (deviceService.isOwner(id, currentUser.getId())) {
            return ResponseEntity.ok(deviceService.getById(id));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device's owner"),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<?> getSwitchButtonsByDeviceTopic(@PathVariable String topic) {
        List<Integer> buttons = deviceService.getDeviceByTopic(topic).stream()
                                            .map(Device::getSwitchButton)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(buttons);
    }

    @PostMapping
    public ResponseEntity<?> save(@CurrentUser UserPrincipal currentUser, @RequestBody Device device) {
        if (roomService.isOwner(device.getRoomId(), currentUser.getId())) {
            return ResponseEntity.ok(new DeviceResponse(deviceService.save(device)));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not room's owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> update(@CurrentUser UserPrincipal currentUser, @RequestBody Device device) {
        if (deviceService.isOwner(device.getId(), currentUser.getId())) {
            return ResponseEntity.ok(new DeviceResponse(deviceService
                    .updateInfoById(device.getId(), device.getLogo(), device.getName())));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device's owner"),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (deviceService.isOwner(id, currentUser.getId())) {
            deviceService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Device has been deleted"));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not device's owner"),
                HttpStatus.BAD_REQUEST);
    }
}

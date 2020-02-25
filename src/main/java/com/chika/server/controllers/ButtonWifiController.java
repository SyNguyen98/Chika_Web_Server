package com.chika.server.controllers;

import com.chika.server.models.product.ButtonWifi;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.product.ButtonWifiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Device requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
 */
@RestController
@RequestMapping("/button_wifi")
public class ButtonWifiController {

    private final ButtonWifiService buttonWifiService;

    public ButtonWifiController(ButtonWifiService buttonWifiService) {
        this.buttonWifiService = buttonWifiService;
    }

    @GetMapping("/room/{roomId}")
    public List<ButtonWifi> getAllByRoomId(@PathVariable String roomId) {
        return buttonWifiService.getAllByRoomId(roomId);
    }

    @GetMapping("/switch/{switchId}")
    public List<ButtonWifi> getAllBySwitchId(@PathVariable String switchId) {
        return buttonWifiService.getAllBySwitchId(switchId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (buttonWifiService.isOwner(id, currentUser.getId())) {
            return ResponseEntity.ok(buttonWifiService.getById(id));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/info")
    public ResponseEntity<?> updateInfo(@CurrentUser UserPrincipal currentUser, @RequestBody ButtonWifi buttonWifi) {
        if (buttonWifiService.isOwner(buttonWifi.getId(), currentUser.getId())) {
            return ResponseEntity.ok(buttonWifiService.updateInfo(buttonWifi.getId(), buttonWifi.getName(), buttonWifi.getRoomId()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/state")
    public ResponseEntity<?> updateState(@CurrentUser UserPrincipal currentUser, @RequestBody ButtonWifi buttonWifi) {
        if (buttonWifiService.isOwner(buttonWifi.getId(), currentUser.getId())) {
            return ResponseEntity.ok(buttonWifiService.updateState(buttonWifi.getId(), buttonWifi.getState()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not owner"),
                HttpStatus.BAD_REQUEST);
    }

    // HISTORY
//    @GetMapping("/{deviceId}/history")
//    public ResponseEntity<?> getAllHistoriesByDeviceId(@CurrentUser UserPrincipal currentUser, @PathVariable String deviceId,
//                                            @RequestParam("page") int page, @RequestParam("size") int size) {
//        if (buttonWifiService.isOwner(deviceId, currentUser.getId())) {
//            return ResponseEntity.ok(buttonWifiService.getAllHistoriesByDeviceId(deviceId, page, size)
//                    .stream()
//                    .map(DeviceHistoryResponse::new)
//                    .collect(Collectors.toList()));
//        }
//        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @DeleteMapping("/{deviceId}/history")
//    public ResponseEntity<?> deleteAllHistoriesByDeviceId(@CurrentUser UserPrincipal currentUser, @PathVariable String deviceId) {
//        if (buttonWifiService.isOwner(deviceId, currentUser.getId())) {
//            buttonWifiService.deleteAllHistoriesByDeviceId(deviceId);
//            return ResponseEntity.ok(new ApiResponse(true, "All device's histories have been deleted"));
//        }
//        return new ResponseEntity<>(new ApiResponse(false, "You are not device owner"),
//                HttpStatus.BAD_REQUEST);
//    }
}

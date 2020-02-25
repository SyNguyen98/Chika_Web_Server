package com.chika.server.controllers;

import com.chika.server.models.product.ButtonRf;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.product.ButtonRfService;
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
@RequestMapping("/button_rf")
public class ButtonRfController {

    private final ButtonRfService buttonRfService;

    public ButtonRfController(ButtonRfService buttonRfService) {
        this.buttonRfService = buttonRfService;
    }

    @GetMapping("/room/{roomId}")
    public List<ButtonRf> getAllByRoomId(@PathVariable String roomId) {
        return buttonRfService.getAllByRoomId(roomId);
    }

    @GetMapping("/switch/{switchId}")
    public List<ButtonRf> getAllBySwitchId(@PathVariable String switchId) {
        return buttonRfService.getAllBySwitchId(switchId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (buttonRfService.isOwner(id, currentUser.getId())) {
            return ResponseEntity.ok(buttonRfService.getById(id));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/info")
    public ResponseEntity<?> updateInfo(@CurrentUser UserPrincipal currentUser, @RequestBody ButtonRf buttonRf) {
        if (buttonRfService.isOwner(buttonRf.getId(), currentUser.getId())) {
            return ResponseEntity.ok(buttonRfService.updateInfo(buttonRf.getId(), buttonRf.getName(), buttonRf.getRoomId()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/state")
    public ResponseEntity<?> updateState(@CurrentUser UserPrincipal currentUser, @RequestBody ButtonRf buttonRf) {
        if (buttonRfService.isOwner(buttonRf.getId(), currentUser.getId())) {
            return ResponseEntity.ok(buttonRfService.updateState(buttonRf.getId(), buttonRf.getState()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not owner"),
                HttpStatus.BAD_REQUEST);
    }
}

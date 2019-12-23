package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.device.Switch;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.SwitchResponse;
import com.chika.server.services.DeviceService;
import com.chika.server.services.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Switch requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/switch")
public class SwitchController {

    private final SwitchService switchService;

    private final DeviceService deviceService;

    public SwitchController(SwitchService switchService, DeviceService deviceService) {
        this.switchService = switchService;
        this.deviceService = deviceService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Switch> getAll() {
        return switchService.getAll();
    }

    @GetMapping("/{id}")
    public Switch getById(@PathVariable String id) {
        return switchService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SwitchResponse save(@RequestParam("numOfDevice") int numOfDevice) {
        Switch newSwitch = switchService.save(new Switch("CA-SW" + numOfDevice));
        for (int i = 0; i < numOfDevice; i++) {
            deviceService.save(new Device("", 0, "", newSwitch.getId()));
        }
        return new SwitchResponse(newSwitch, deviceService.getAllBySwitchId(newSwitch.getId()));
    }

    @PutMapping("/name")
    public Switch updateName(@RequestBody Switch _switch) {
        return switchService.updateName(_switch.getId(), _switch.getName());
    }

    @PutMapping("/user")
    public Switch updateUser(@RequestBody Switch _switch) {
        return switchService.updateUser(_switch.getId(), _switch.getUserId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        for (Device device : switchService.getById(id).getDevices()) {
            deviceService.delete(device.getId());
        }
        switchService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Switch has been deleted"));
    }
}

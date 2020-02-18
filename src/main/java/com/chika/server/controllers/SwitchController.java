package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.product.Switch;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.products.SwitchResponse;
import com.chika.server.payload.responses.products.SwitchResponseForAdmin;
import com.chika.server.services.DeviceService;
import com.chika.server.services.SwitchService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Switch requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 17-02-2020
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
    public List<SwitchResponseForAdmin> getAll() {
        return switchService.getAll().stream()
                .map(SwitchResponseForAdmin::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SwitchResponse getById(@PathVariable String id) {
        return new SwitchResponse(switchService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/num_of_device/{numOfDevice}")
    public SwitchResponseForAdmin save(@PathVariable int numOfDevice) {
        Switch newSwitch = switchService.save(new Switch("CA-SW" + numOfDevice, numOfDevice));
        for (int i = 0; i < numOfDevice; i++) {
            deviceService.save(new Device(0, newSwitch.getId()));
        }
        return new SwitchResponseForAdmin(newSwitch, deviceService.getAllBySwitchId(newSwitch.getId()));
    }

    @PutMapping("/name")
    public Switch updateName(@RequestBody Switch _switch) {
        return switchService.updateName(_switch.getId(), _switch.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/user")
    public Switch updateUser(@RequestBody Switch _switch) {
        return switchService.updateUser(_switch.getId(), _switch.getUserId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        switchService.getById(id).getDevices()
                .forEach(device -> deviceService.delete(device.getId()));
        switchService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Switch has been deleted"));
    }
}

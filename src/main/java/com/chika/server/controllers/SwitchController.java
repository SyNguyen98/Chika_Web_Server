package com.chika.server.controllers;

import com.chika.server.models.house.Device;
import com.chika.server.models.device.Switch;
import com.chika.server.services.DeviceService;
import com.chika.server.services.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-11-2019
 */
@RestController
@RequestMapping("/switch")
public class SwitchController {

    @Autowired
    private SwitchService switchService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{id}")
    public Switch getById(@PathVariable String id) {
        return switchService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Switch save(@RequestParam("numOfDevice") int numOfDevice) {
        Switch newSwitch = switchService.save(new Switch());
        for (int i = 0; i < numOfDevice; i++) {
            deviceService.save(new Device("", 0, "", newSwitch.getId(), (long) 0));
        }
        return newSwitch;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable String id) {
        for (Device device : switchService.getById(id).getDevices()) {
            deviceService.delete(device.getId());
        }
        switchService.delete(id);
        return true;
    }
}

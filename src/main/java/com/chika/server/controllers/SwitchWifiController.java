package com.chika.server.controllers;

import com.chika.server.models.product.ButtonWifi;
import com.chika.server.models.product.SwitchWifi;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.products.SwitchResponse;
import com.chika.server.payload.responses.products.SwitchWifiResponseForAdmin;
import com.chika.server.services.product.ButtonWifiService;
import com.chika.server.services.product.SwitchWifiService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Switch Wifi requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 24-02-2020
 */
@RestController
@RequestMapping("/switch_wifi")
public class SwitchWifiController {

    private final SwitchWifiService switchWifiService;

    private final ButtonWifiService buttonWifiService;

    public SwitchWifiController(SwitchWifiService switchWifiService, ButtonWifiService buttonWifiService) {
        this.switchWifiService = switchWifiService;
        this.buttonWifiService = buttonWifiService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<SwitchWifiResponseForAdmin> getAll() {
        return switchWifiService.getAll().stream()
                .map(SwitchWifiResponseForAdmin::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SwitchResponse getById(@PathVariable String id) {
        return new SwitchResponse(switchWifiService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/num_of_button/{numOfButton}")
    public SwitchWifiResponseForAdmin save(@PathVariable int numOfButton) {
        SwitchWifi switchWifi = switchWifiService.save(new SwitchWifi("CA-SWW" + numOfButton, numOfButton));
        for (int i = 0; i < numOfButton; i++) {
            buttonWifiService.save(new ButtonWifi(switchWifi.getId()));
        }
        return new SwitchWifiResponseForAdmin(switchWifi, buttonWifiService.getAllBySwitchId(switchWifi.getId()));
    }

    @PutMapping("/name")
    public SwitchWifi updateName(@RequestBody SwitchWifi _switchWifi) {
        return switchWifiService.updateName(_switchWifi.getId(), _switchWifi.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/user")
    public SwitchWifi updateUser(@RequestBody SwitchWifi switchWifi) {
        return switchWifiService.updateUser(switchWifi.getId(), switchWifi.getUserId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        switchWifiService.getById(id).getButtonWifis()
                .forEach(device -> buttonWifiService.delete(device.getId()));
        switchWifiService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Switch Wifi has been deleted"));
    }
}

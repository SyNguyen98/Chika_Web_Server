package com.chika.server.controllers;

import com.chika.server.models.product.SwitchRf;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.products.SwitchRfResponseForAdmin;
import com.chika.server.services.product.SwitchRfService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Switch Rf requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 04-04-2020
 */
@RestController
@RequestMapping("/switch_rf")
public class SwitchRfController {

    private final SwitchRfService switchRfService;

    public SwitchRfController(SwitchRfService switchRfService) {
        this.switchRfService = switchRfService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<SwitchRfResponseForAdmin> getAll() {
        return switchRfService.getAll().stream()
                .map(SwitchRfResponseForAdmin::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SwitchRfResponseForAdmin getById(@PathVariable String id) {
        return new SwitchRfResponseForAdmin(switchRfService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/num_of_button/{numOfButton}/channel/{channel}")
    public SwitchRfResponseForAdmin save(@PathVariable int numOfButton, @PathVariable long channel) {
        SwitchRf switchRf = switchRfService.save(new SwitchRf("CA-SR" + numOfButton, numOfButton, channel));
        return new SwitchRfResponseForAdmin(switchRf);
    }

    @PutMapping("/name")
    public SwitchRf updateName(@RequestBody SwitchRf switchRf) {
        return switchRfService.updateName(switchRf.getId(), switchRf.getName());
    }

    @PutMapping("/channel")
    public SwitchRf updateChannel(@RequestBody SwitchRf switchRf) {
        return switchRfService.updateChannel(switchRf.getId(), switchRf.getChannel());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HOME_MASTER')")
    @PutMapping("/user")
    public SwitchRf updateUser(@RequestBody SwitchRf switchRf) {
        return switchRfService.updateUser(switchRf.getId(), switchRf.getUserId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        switchRfService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Switch Rf has been deleted"));
    }
}

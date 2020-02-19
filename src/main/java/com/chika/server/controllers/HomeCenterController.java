package com.chika.server.controllers;

import com.chika.server.models.product.HomeCenter;
import com.chika.server.models.product.ModuleIr;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.products.HomeCenterResponse;
import com.chika.server.payload.responses.products.HomeCenterResponseForAdmin;
import com.chika.server.services.HomeCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home_center")
public class HomeCenterController {

    private final HomeCenterService homeCenterService;

    public HomeCenterController(HomeCenterService homeCenterService) {
        this.homeCenterService = homeCenterService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<HomeCenterResponseForAdmin> getAll() {
        return homeCenterService.getAll().stream()
                .map(HomeCenterResponseForAdmin::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HomeCenterResponse getById(@PathVariable String id) {
        return new HomeCenterResponse(homeCenterService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public HomeCenterResponseForAdmin save() {
        return new HomeCenterResponseForAdmin(homeCenterService.save(new HomeCenter()));
    }

    @PutMapping("/name")
    public HomeCenter updateName(@RequestBody ModuleIr moduleIr) {
        return homeCenterService.updateName(moduleIr.getId(), moduleIr.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/user")
    public HomeCenter updateUser(@RequestBody ModuleIr moduleIr) {
        return homeCenterService.updateUser(moduleIr.getId(), moduleIr.getUserId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        homeCenterService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Home Center has been deleted"));
    }
}
